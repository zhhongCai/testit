package com.test.it.jdktest.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏：等待多个线程完成
 * Author: caizh
 * CreateTime: 2014/10/15 10:39
 * Version: 1.0
 */
public class CyclicBarrierTest {
    protected static final int PARTIES = 10;
    private CyclicBarrier barrier;

    public CyclicBarrierTest() {
        this(PARTIES);
    }

    public CyclicBarrierTest(int parties) {
        barrier = new CyclicBarrier(parties, new Runnable(){
            @Override
            public void run() {
                System.out.println("All work done!");
            }
        });
    }

    class Worker implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("do something!");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

    public void start() {
        for(int i = 0; i < PARTIES; i++) {
            Thread thread = new Thread(new Worker());
            thread.start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.start();
    }
}
