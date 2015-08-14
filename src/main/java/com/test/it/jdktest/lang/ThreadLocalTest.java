package com.test.it.jdktest.lang;

import java.util.Map;

/**
 * Author: caizh
 * CreateTime: 2014/11/24 9:50
 * Version: 1.0
 */
public class ThreadLocalTest {

    public static void main(StringTest[] args) {
        Thread thread1 = new Thread(new EchoRunnable());
        Thread thread2 = new Thread(new EchoRunnable());
        Thread thread3 = new Thread(new EchoRunnable());

    }
}

class EchoRunnable implements Runnable {
    private ThreadLocal<Map<StringTest, Object>> threadData = new ThreadLocal<Map<StringTest, Object>>();

    @Override
    public void run() {
        System.out.println("echo :" + threadData.get());
    }

    public ThreadLocal<Map<StringTest, Object>> getThreadData() {
        return threadData;
    }

    public void setThreadData(ThreadLocal<Map<StringTest, Object>> threadData) {
        this.threadData = threadData;
    }
}