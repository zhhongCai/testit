package com.test.it.jdktest.jdk8.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Learning jdk java.util.concurrent.*
 * Author: caizh
 * CreateTime: 2014/9/12 9:06
 * Version: 1.0
 */
public class AtomicIntegerWithLock {
    private int value;

    private Lock lock = new ReentrantLock();

    AtomicIntegerWithLock() {
        super();
    }

    AtomicIntegerWithLock(int value) {
        this.value = value;
    }

    public final int get() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }

    public final void set(int newValue) {
        lock.lock();
        try {
            value = newValue;
        } finally {
            lock.unlock();
        }
    }

    public final int getAndSet(int newValue) {
        lock.lock();
        try {
            int retValue = value;
            value = newValue;
            return retValue;
        } finally {
            lock.unlock();
        }
    }

    public final boolean compareAndSet(int expect, int newValue) {
        lock.lock();
        try {
            if(value == expect) {
                value = newValue;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

}
