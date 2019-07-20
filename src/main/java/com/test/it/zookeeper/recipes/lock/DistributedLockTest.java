package com.test.it.zookeeper.recipes.lock;

import com.google.common.collect.Lists;
import com.test.it.zookeeper.recipes.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-07-16 15:32
 * @Description:
 */
public class DistributedLockTest extends BaseTest {

    private ExecutorService executorService;

    private int queueSize = 1024;

    @Before
    @Override
    public void before() throws IOException {
        super.before();

        executorService = new ThreadPoolExecutor(4, 40, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @After
    @Override
    public void after() throws InterruptedException {
        super.after();
        if (executorService != null) {
            executorService.shutdown();
            if (!executorService.isShutdown()) {
                executorService.awaitTermination(5L, TimeUnit.SECONDS);
            }

            executorService.shutdownNow();
        }
    }

    @Test
    public void test() throws InterruptedException {
        String key = "test2";
        DistributedLock lock = new DistributedLock(zk, "test");

        CountDownLatch beginLatch = new CountDownLatch(1);

        int n = queueSize;
        List<Future<Integer>> futureList = Lists.newArrayListWithCapacity(n);
        for (int i = 0; i < n; i++) {
            int index = i;
            futureList.add(executorService.submit(() -> {
                boolean locked = false;

                try {
                    beginLatch.await();

                    locked = lock.lock(key, 2 + index, TimeUnit.SECONDS);
                    if (locked) {
                        System.out.println("获取锁成功" + index);

                        System.out.println("do sth...." + index);
                        return 1;
                    } else {
                        System.out.println("获取锁失败" + index);
                        return 0;
                    }
                } finally {
                    if (locked) {
                        lock.releaseLock();
                    }
                }
            }));

        }

        beginLatch.countDown();

        int sum = 0;
        for (Future<Integer> f : futureList) {

            try {
                sum += f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        Assert.assertEquals(n, sum);

        Thread.sleep(300000);
    }

    @Test
    public void testMultiLock() throws InterruptedException {
        DistributedLock lock = new DistributedLock(zk);

        String key = "multiLock";

        CountDownLatch beginLatch = new CountDownLatch(1);

        int n = queueSize;
        List<Future<Integer>> futureList = Lists.newArrayListWithCapacity(n);
        for (int i = 0; i < n; i++) {
            int index = i;
            futureList.add(executorService.submit(() -> {
                boolean locked = false;
                try {

                    beginLatch.await();

                    String k = key + index;
                    locked = lock.lock(k, 2 + index, TimeUnit.SECONDS);
                    if (locked) {
                        System.out.println("获取锁成功" + k);

                        System.out.println("do sth...." + k);
                        return 1;
                    } else {
                        System.out.println("获取锁失败" + k);
                        return 0;
                    }
                } finally {
                    if (locked) {
                        lock.releaseLock();
                    }
                }
            }));

        }

        beginLatch.countDown();

        int sum = 0;
        for (Future<Integer> f : futureList) {

            try {
                sum += f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        Assert.assertEquals(n, sum);

        Thread.sleep(300000);
    }
}

