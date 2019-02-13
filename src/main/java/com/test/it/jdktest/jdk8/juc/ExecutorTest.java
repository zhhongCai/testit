package com.test.it.jdktest.jdk8.juc;

import java.util.concurrent.*;

/**
 * Author: caizh
 * CreateTime: 2014/10/13 10:04
 * Version: 1.0
 */
public class ExecutorTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(5);
        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(new TestRunnable(), 1, 30, TimeUnit.SECONDS);
        System.out.print(scheduledFuture.get());
        scheduledThreadPool.shutdown();
    }
}

class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("do job");
    }
}