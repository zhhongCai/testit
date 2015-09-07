package com.test.it.jdktest.juc;

/**
 * Created by caizh on 2015/9/1 0001.
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        DoSth dosth = new DoSth();
        dosth.start();

        Thread.sleep(3000L);

        dosth.assign();
    }
}

class DoSth implements Runnable {

    private boolean available = false;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public synchronized void assign() {
        setAvailable(true);
        notifyAll();
        System.out.println("available now");
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private synchronized void doit() {
        try {
            while(!available) {
                wait();
            }
            available = true;
            notifyAll();
            System.out.println("do sth...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doit();
    }
}
