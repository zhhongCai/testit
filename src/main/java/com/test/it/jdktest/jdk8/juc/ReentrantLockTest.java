package com.test.it.jdktest.jdk8.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: caizh
 * CreateTime: 2014/12/18 14:28
 * Version: 1.0
 */
public class ReentrantLockTest<T> {
    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();
    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();
    private transient int c = 0;
    private List<T> list = null;

    public ReentrantLockTest() {
        this(1024);
    }

    public ReentrantLockTest(int capcity) {
        if(capcity <= 0) {
            throw new IllegalArgumentException();
        }
        list = new ArrayList<T>(capcity);
        c = capcity;
    }

    private void signalNotEmpty() {
        final ReentrantLock tackLock = this.takeLock;
        tackLock.lock();
        try {
            notEmpty.signalAll();
        } finally {
            tackLock.unlock();
        }
        System.out.println("signalNotEmpty");
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signalAll();
        } finally {
            putLock.unlock();
        }
        System.out.println("signalNotFull");
    }

    public T take() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        T obj = null;
        try {
            if(!list.isEmpty()) {
                obj =list.remove(0);
            } else {
                signalNotFull();
            }
        } finally {
            takeLock.unlock();
        }
        if(list.size() < c) {
            signalNotFull();
        }
        return obj;
    }

    public boolean put(T obj) {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        boolean t = false;
        try {
            if(!(list.size() > c)) {
                t = list.add(obj);
            } else {
                signalNotEmpty();
            }
        } finally {
            putLock.unlock();
        }
        if(list.size() > 0) {
            signalNotEmpty();
        }
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLockTest<String> test = new ReentrantLockTest<String>(256);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1024; i++) {
                    System.out.println(test.take());
                }
            }
        });

        for(int i = 0; i < 1024; i++) {
            test.put("test data " + i);
        }
        thread.start();

        thread.join();

        System.out.println("done");
    }
}
