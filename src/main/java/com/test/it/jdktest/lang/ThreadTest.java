package com.test.it.jdktest.lang;

/**
 * Author: caizh
 * CreateTime: 2014/11/18 13:25
 * Version: 1.0
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1 do something cost time");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1 done");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2 do something cost time");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2 done");
            }
        });

        thread2.start();
        thread.start();

        thread.join();
        System.out.println("thread 1 done!");
        thread2.join();
        System.out.println("thread 2 done!");

        System.out.println("main done");
        return;
    }
}
