package com.test.it.jvmtest;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <<JVM_Troubleshooting_Guide.pdf>>
 * Created by caizh on 16-7-3.
 */
public class DeadLockTask {

    // Object used for FLAT lock
    private final Object sharedObject = new Object();
    // ReentrantReadWriteLock used for WRITE & READ locks
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void executeTask1() {
        // 1. Attempt to acquire a ReentrantReadWriteLock READ lock
        lock.readLock().lock();

        try {
            // Wait 2 seconds to simulate some work...
            Thread.sleep(2000);
        } catch (Throwable any) {
            any.printStackTrace();
        }

        try {
            // 2. Attempt to acquire a Flat lock...
            synchronized (sharedObject) {}
        }

        // Remove the READ lock
        finally {
            lock.readLock().unlock();
        }

        System.out.println("executeTask1() :: Work Done!");
    }

    public void executeTask2() {
        // 1. Attempt to acquire a Flat lock
        synchronized (sharedObject) {
            try {
                // Wait 2 seconds to simulate some work...
                Thread.sleep(2000);
            } catch (Throwable any) {
                any.printStackTrace();
            }

            // 2. Attempt to acquire a WRITE lock
            lock.writeLock().lock();
            try {
                // Do nothing
            }
            finally {
                lock.writeLock().unlock();
            }
        }

        System.out.println("executeTask2() :: Work Done!");
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }
}
