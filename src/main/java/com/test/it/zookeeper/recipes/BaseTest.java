package com.test.it.zookeeper.recipes;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * @Author: theonecai
 * @Date: Create in 2019-07-16 15:32
 * @Description:
 */
public class BaseTest {
    protected ZooKeeper zk = null;

    @Before
    public void before() throws IOException {
        zk = new ZooKeeper("localhost:2181", 3000000, watchedEvent -> {
            System.out.println("BaseTest Watcher fired on path: " + watchedEvent.getPath() + " state: " +
                    watchedEvent.getState() + " type " + watchedEvent.getType());
        });
    }

    @After
    public void after() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }
}
