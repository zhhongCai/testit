package com.test.it.jdktest.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列，先进先出
 * @Author: caizhh
 * @Date: Create in 18-9-11 上午8:31
 * @Description:
 */
public class BoundedQueue<T> {
    private int addIndex, removeIndex, count;

    private Object[] item;

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public BoundedQueue(int size) {
        item = new Object[size];
    }

    public int add(T e) throws InterruptedException {
        lock.lock();
        try {
            if (count == item.length) {
                System.out.println("add await");
                notFull.await();
            }
            int j = addIndex;
            item[addIndex] = e;
            if (++addIndex == item.length) {
                addIndex = 0;
            }
            count++;
            notEmpty.signal();

            return j;
        } finally {
            System.out.println("add unlock");
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0) {
                System.out.println("remove await");
                notEmpty.await();
            }
            T e = (T) item[removeIndex];
            if (++removeIndex == item.length) {
                removeIndex = 0;
            }
            count--;
            notFull.signal();
            return e;
        } finally {
            System.out.println("remove unlock");
            lock.unlock();
        }
    }

    public int getAddIndex() {
        return addIndex;
    }

    public int getRemoveIndex() {
        return removeIndex;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedQueue<String> queue = new BoundedQueue<>(10);
        Thread thread = new Thread("add") {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        int ret = queue.add("test" + i);
                        System.out.println(String.format("add %s, addIndex=%s, removeIndex=%s, count=%s", ret, queue.getAddIndex(), queue.getRemoveIndex(), queue.getCount()));
                        Thread.sleep(100L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread("remove") {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        String e = queue.remove();
                        System.out.println(String.format("remove %s, addIndex=%s, removeIndex=%s, count=%s", e, queue.getAddIndex(), queue.getRemoveIndex(), queue.getCount()));
                        Thread.sleep(100L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        Thread.sleep(3000L);
        thread2.start();


    }
}
