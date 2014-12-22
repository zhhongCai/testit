package com.test.it.jdktest.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Author: caizh
 * CreateTime: 2014/12/17 15:08
 * Version: 1.0
 */
public class SynchronousQueueTest {

    public static SynchronousQueue synchronousQueue = new SynchronousQueue();

    public static void main(String[] args) throws InterruptedException {
        boolean t = synchronousQueue.offer("somedata");
        System.out.println("offert return :" + t);

        Object data = synchronousQueue.poll();
        System.out.println("pool return data: " + data);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronousQueue.put("test sth");
                    System.out.println("put test sth");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Object data = synchronousQueue.take();
                    System.out.println("thread 2 take: " + data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(1000L);

        executorService.shutdown();

        System.out.println("done");
    }
}
