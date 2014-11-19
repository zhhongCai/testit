package com.test.it.jdktest.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁： 多个线程同时开始执行，并等待所有线程执行完毕
 * Author: caizh
 * CreateTime: 2014/10/15 10:02
 * Version: 1.0
 */
public class LatchTest {
    protected static final int THREAD_NUM = 10;
    private CountDownLatch startLatch = new CountDownLatch(1);
    private CountDownLatch endLatch = new CountDownLatch(THREAD_NUM);

    public void start() throws InterruptedException {
        for(int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startLatch.await();
                        System.out.println("job done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        endLatch.countDown();
                    }
                }
            });
            thread.start();
        }

        long before = System.currentTimeMillis();
        startLatch.countDown();
        endLatch.await();
        System.out.println("total cost time: " + (System.currentTimeMillis() - before));
    }

    public static void main(String[] args) throws InterruptedException {
        LatchTest latchTest = new LatchTest();
        latchTest.start();
    }

}
