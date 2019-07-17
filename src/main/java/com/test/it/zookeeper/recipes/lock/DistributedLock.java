package com.test.it.zookeeper.recipes.lock;

import com.test.it.zookeeper.recipes.queue.DistributedQueue;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
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

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-07-16 14:47
 * @Description:
 */
public class DistributedLock {
    private Logger logger = LoggerFactory.getLogger(DistributedQueue.class);

    private ZooKeeper zookeeper;

    private String basePath = "/distLock";

    private List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;

    public DistributedLock(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
        try {
            if (zookeeper.exists(basePath, false) == null) {
                zookeeper.create(basePath, new byte[0], acl, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean lock(String key, int timeout, TimeUnit timeUnit) {
        try {
            String current = zookeeper.create(basePath + "/" + key + "-", new byte[0], acl, CreateMode.EPHEMERAL_SEQUENTIAL);

            return lockInternal(current, TimeUnit.MILLISECONDS.convert(timeout, timeUnit));

        } catch (KeeperException | InterruptedException e) {
            logger.error("lock error", e);
            return false;
        }
    }

    private boolean lockInternal(String current, long timeout) throws KeeperException, InterruptedException {
        do {
            logger.info("开始获取锁" + current);
            long start = System.currentTimeMillis();
            List<String> children = zookeeper.getChildren(basePath, false);

            String min = findMin(children);
            logger.info("getChildren: " + children);

            String currentNode = current.substring(basePath.length() + 1);
            if (min.equals(currentNode)) {
                logger.info("成功获得锁" + current);
                return true;
            }

            String lastBeforeCurrent = lastBeforeCurrent(children, currentNode);
            CountDownLatch latch = new CountDownLatch(1);

            logger.info("lastBeforeCurrent=" + lastBeforeCurrent);
            Stat exists = zookeeper.exists(basePath + "/" + lastBeforeCurrent, watcherEvent -> {
                System.out.println(current + ",Watcher fired on path: " + watcherEvent.getPath() + " state: " +
                        watcherEvent.getState() + " type " + watcherEvent.getType());
                if (watcherEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                    latch.countDown();
                }
            });

            timeout = timeout - (System.currentTimeMillis() - start);
            if (timeout < 0) {
                logger.error("获取锁超时了");
                return false;
            }
            if (exists == null) {
                continue;
            }

            if (!latch.await(timeout, TimeUnit.MILLISECONDS)) {
                logger.error("获取锁超时了");
                return false;
            }
        } while (true);
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

    private String findMin(List<String> children) {
        children.sort(Comparator.naturalOrder());
        return children.get(0);
    }

    public boolean releaseLock() {
        try {
            List<String> children = zookeeper.getChildren(basePath, false);
            String min = findMin(children);
            zookeeper.delete(basePath + "/" + min, -1);
            logger.info("releaseLock: " + basePath + "/" + min);
            return true;
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
            return false;
        }
    }
}
