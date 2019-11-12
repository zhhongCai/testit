package com.test.it.zookeeper.recipes.queue;

import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: theonecai
 * @Date: Create in 2019-07-16 13:24
 * @Description:
 */
public class DistributedQueue {

    private Logger logger = LoggerFactory.getLogger(DistributedQueue.class);

    private ZooKeeper zookeeper;

    private String queuePath = "/queueName";

    private String prefix = "qn-";

    private List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;

    public DistributedQueue(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public boolean offer(byte[] data) throws KeeperException, InterruptedException {
        for(;;){
            try{
                zookeeper.create(queuePath+"/"+prefix, data, acl, CreateMode.PERSISTENT_SEQUENTIAL);
                return true;
            }catch(KeeperException.NoNodeException e){
                zookeeper.create(queuePath, new byte[0], acl, CreateMode.PERSISTENT);
            }
        }
    }

    public byte[] take() throws KeeperException, InterruptedException {
        Map<Long,String> orderedChildren;
        // Same as for element.  Should refactor this.
        while(true){
            LatchChildWatcher childWatcher = new LatchChildWatcher();
            try{
                orderedChildren = orderedChildren(childWatcher);
            }catch(KeeperException.NoNodeException e){
                zookeeper.create(queuePath, new byte[0], acl, CreateMode.PERSISTENT);
                continue;
            }
            if(orderedChildren.size() == 0){
                childWatcher.await();
                continue;
            }

            for(String headNode : orderedChildren.values()){
                String path = queuePath +"/"+headNode;
                try{
                    byte[] data = zookeeper.getData(path, false, null);
                    zookeeper.delete(path, -1);
                    return data;
                }catch(KeeperException.NoNodeException e){
                    // Another client deleted the node first.
                }
            }
        }
    }

    private Map<Long, String> orderedChildren(LatchChildWatcher watcher) throws KeeperException, InterruptedException {
        List<String> children = zookeeper.getChildren(queuePath, watcher);
        if (CollectionUtils.isEmpty(children)) {
            return Collections.emptyMap();
        }
        Map<Long, String> orderdMap = new TreeMap<>();
        children.forEach(child -> {
            Long id = Long.valueOf(child.substring(prefix.length()));
            orderdMap.put(id, child);
        });
        return orderdMap;
    }

    public class LatchChildWatcher implements Watcher {

        private CountDownLatch latch = new CountDownLatch(1);

        @Override
        public void process(WatchedEvent event) {
            logger.info("Watcher fired on path: " + event.getPath() + " state: " +
                    event.getState() + " type " + event.getType());
            latch.countDown();
        }

        public void await() throws InterruptedException {
            latch.await();
        }
    }
}
