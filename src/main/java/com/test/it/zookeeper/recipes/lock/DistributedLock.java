package com.test.it.zookeeper.recipes.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 基于zookeeper的分布式独占锁
 *
 * @Author: zhenghong.cai
 * @Date: Create in 2019-07-16 14:47
 * @Description:
 */
public class DistributedLock {
    private Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    private ZooKeeper zookeeper;

    private String basePath = "/distLock";

    private String domain;

    private List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;

    /**
     * 线程变量保存当前节点
      */
    private ThreadLocal<String> currentKey = new ThreadLocal<>();

    public DistributedLock(ZooKeeper zookeeper) {
        this(zookeeper, "default");
    }

    public DistributedLock(ZooKeeper zookeeper, String domain) {
        this.zookeeper = zookeeper;
        this.domain = domain;
        try {
            String parentPath = getParentPath();
            if (zookeeper.exists(parentPath, false) == null) {
                zookeeper.create(parentPath, new byte[0], acl, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            logger.error("创建basePath失败" + e.getMessage(), e);
        }
    }

    public String getParentPath() {
        return basePath + "/" + domain;
    }

    /**
     * 获取锁流程（参见zookeeper源码zookeeper-docs/src/main/resources/markdown/recipes.md）：
     * 1. Call create( ) with a pathname of "locknode/guid-lock-" and the sequence and ephemeral flags set.
     *    The guid is needed in case the create() result is missed. See the note below.
     * 2. Call getChildren( ) on the lock node without setting the watch flag (this is important to avoid
     *    the herd effect).
     * 3. If the pathname created in step 1 has the lowest sequence number suffix, the client has the lock and
     *    the client exits the protocol.
     * 4. The client calls exists( ) with the watch flag set on the path in the lock directory with the next
     *    lowest sequence number.
     * 5. if exists( ) returns null, go to step 2. Otherwise, wait for a notification for the pathname from
     *    the previous step before going to step 2.
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean lock(String key, int timeout, TimeUnit timeUnit) {
        try {
            String current = zookeeper.create(getParentPath() + "/" + key + "-", new byte[0], acl, CreateMode.EPHEMERAL_SEQUENTIAL);
            currentKey.set(current);
            return lockInternal(current, timeUnit.toMillis(timeout));

        } catch (KeeperException | InterruptedException e) {
            logger.error("lock error", e);
            return false;
        }
    }

    private boolean lockInternal(String current, long timeout) throws KeeperException, InterruptedException {
        String parentPath = getParentPath();
        String currentNode = current.substring(parentPath.length() + 1);
        LatchChildWatcher watcher = new LatchChildWatcher(currentNode);

        for(;;) {
            logger.info("开始获取锁" + current);
            long start = System.currentTimeMillis();
            List<String> children = zookeeper.getChildren(parentPath, false);

            List<String> currentKeySortedList = filterCurrentKeySortedList(children, currentNode);
            logger.info("currentKeySortedList: " + currentKeySortedList);

            // 单前是最小的那个节点
            if (currentKeySortedList.get(0).equals(currentNode)) {
                logger.info("成功获得锁" + current);
                return true;
            }

            String lastBeforeCurrent = lastBeforeCurrent(currentKeySortedList, currentNode);
            logger.info(currentNode + ",lastBeforeCurrent=" + lastBeforeCurrent);

            Stat exists = zookeeper.exists(parentPath + "/" + lastBeforeCurrent, watcher);

            timeout = timeout - (System.currentTimeMillis() - start);
            if (timeout < 0) {
                logger.error("获取锁超时了");
                children.clear();
                currentKeySortedList.clear();
                return false;
            }

            if (exists == null) {
                children.clear();
                currentKeySortedList.clear();
                continue;
            }

            if (!watcher.await(timeout, TimeUnit.MILLISECONDS)) {
                logger.error("await超时了");
                children.clear();
                currentKeySortedList.clear();
                return false;
            }
        }
    }


    /**
     * 当前节点上一个顺序节点
     * @param children
     * @param current
     * @return
     */
    private String lastBeforeCurrent(List<String> children, String current) {
        int size = children.size();
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (current.equals(children.get(i))) {
                index = i;
                break;
            }
        }
        if (index < 1) {
            index = 1;
        }
        return children.get(index - 1);
    }

    /**
     * 过滤相同key前缀的节点名称并按自然顺序排序
     * @param children
     * @param currentNode
     * @return
     */
    private List<String> filterCurrentKeySortedList(List<String> children, String currentNode) {
        String key = currentNode.substring(0, currentNode.indexOf("-"));
        return children.stream().filter((k) -> k.startsWith(key))
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    public boolean releaseLock() {
        try {
            String current = currentKey.get();
            zookeeper.delete(current, -1);
            currentKey.remove();
            logger.info("releaseLock: " + current);
            return true;
        } catch (InterruptedException | KeeperException e) {
            logger.error("释放锁失败:" + e.getMessage(), e);
            return false;
        }
    }

    /**
     * 监听节点删除事件watcher
     */
    public class LatchChildWatcher implements Watcher {

        private CountDownLatch latch = new CountDownLatch(1);

        private String current;

        public LatchChildWatcher(String current) {
            this.current = current;
        }

        @Override
        public void process(WatchedEvent watcherEvent) {
            System.out.println(current + ",Watcher fired on path: " + watcherEvent.getPath() + " state: " +
                    watcherEvent.getState() + " type " + watcherEvent.getType());
            // 删除节点事件
            if (watcherEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                latch.countDown();
            }
        }

        public boolean await(Long timeout, TimeUnit timeUnit) throws InterruptedException {
            return latch.await(timeout, timeUnit);
        }
    }
}
