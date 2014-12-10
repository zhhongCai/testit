package com.test.it.jdktest.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: caizh
 * CreateTime: 2014/11/24 9:50
 * Version: 1.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new EchoRunnable());
        Thread thread2 = new Thread(new EchoRunnable());
        Thread thread3 = new Thread(new EchoRunnable());

        thread1
    }
}

class EchoRunnable implements Runnable {
    private ThreadLocal<Map<String, Object>> threadData = new ThreadLocal<Map<String, Object>>();

    @Override
    public void run() {
        System.out.println("echo :" + threadData.get());
    }

    public ThreadLocal<Map<String, Object>> getThreadData() {
        return threadData;
    }

    public void setThreadData(ThreadLocal<Map<String, Object>> threadData) {
        this.threadData = threadData;
    }
}