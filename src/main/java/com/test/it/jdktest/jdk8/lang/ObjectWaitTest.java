package com.test.it.jdktest.jdk8.lang;

/**
 * Author: caizh
 * CreateTime: 2014/11/18 13:48
 * Version: 1.0
 */
public class ObjectWaitTest {

    public static void main(String[] args) throws InterruptedException {
        final String a = "I'm the only one!";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hold resource and do sth, maybe cost some time");
                System.out.println(a);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    a.notifyAll();
                }
                System.out.println("ok, I'm done");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait for some resource");
                try {
                    synchronized (a) {
                        a.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get resource: " + a);
                System.out.println("work done");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3 wait for some resource");
                try {
                    synchronized (a) {
                        a.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3 get resource: " + a);
                System.out.println("3 work done");
            }
        });

        thread3.start();
        thread2.start();
        thread.start();

        System.out.println("work begin...");

        thread2.join();
        thread3.join();

        System.out.println("main work done");

    }
}
