package com.test.it.jdktest.jdk8.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue 生产者-消费者
 * Author: caizh
 * CreateTime: 2014/10/15 14:12
 * Version: 1.0
 */
public class BlockingQueueTest {
    private static BlockingQueue<String> msgBlockingQueue = new LinkedBlockingQueue<String>(100);

    public static void mockProducerAndCosumer() throws InterruptedException {
        Thread prod = new Thread(new Producer<String>(msgBlockingQueue, "msg hhhhh"));
        Thread cosumer = new Thread(new Cosumer<String>(msgBlockingQueue));
        prod.start();
        cosumer.start();
    }

    public static void main(String[] args) throws InterruptedException {
        mockProducerAndCosumer();
    }
}

/**
 * 生产者
 * @param <T>
 */
class Producer<T> implements Runnable {
    private BlockingQueue<T> blockingQueue;

    public Producer(BlockingQueue<T> blockingQueue, T data) {
        this.blockingQueue = blockingQueue;
        this.data = data;
    }

    public BlockingQueue<T> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private T data;

    @Override
    public void run() {
        while(true) {
            try {
                getBlockingQueue().offer(data, 1000, TimeUnit.MILLISECONDS);
//                Thread.sleep(500);
                System.out.println("offer data: " + data);
            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

/**
 * 消费者
 * @param <T>
 */
class Cosumer<T> implements Runnable {
    private BlockingQueue<T> blockingQueue;

    public Cosumer(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public BlockingQueue<T> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private T data;

    @Override
    public void run() {
        while(true) {
            try {
                data = getBlockingQueue().poll(1000, TimeUnit.MILLISECONDS);
//                Thread.sleep(500);
                System.out.println("-->poll data: " + data);
            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
