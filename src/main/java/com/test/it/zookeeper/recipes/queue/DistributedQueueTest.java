package com.test.it.zookeeper.recipes.queue;

import com.test.it.zookeeper.recipes.BaseTest;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;

/**
 * @Author: theonecai
 * @Date: Create in 2019-07-16 13:45
 * @Description:
 */
public class DistributedQueueTest extends BaseTest {


    @Test
    public void test() throws KeeperException, InterruptedException {

        DistributedQueue queue = new DistributedQueue(zk);

        queue.offer("test1".getBytes());
        queue.offer("test2".getBytes());
        queue.offer("test3".getBytes());
        queue.offer("test4".getBytes());

        System.out.println(new String(queue.take()));
        System.out.println(new String(queue.take()));
        System.out.println(new String(queue.take()));
        System.out.println(new String(queue.take()));

    }
}
