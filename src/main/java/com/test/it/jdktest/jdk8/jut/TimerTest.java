package com.test.it.jdktest.jdk8.jut;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: caizh
 * CreateTime: 2014/10/9 16:01
 * Version: 1.0
 */
public class TimerTest {
    private Timer timer = new Timer("testTimer");

    private MyTask myTask = new MyTask();

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void start() {
        System.out.println("starting timer...");
        timer.schedule(myTask, 1000, 5000);
        myTask.cancel();
        System.out.println("timer started");
    }

    public void stop() {
        System.out.println("stoping timer...");
        timer.cancel();
        System.out.println("timer stoped");
    }

    public static void main(String[] args) throws InterruptedException {
        TimerTest test = new TimerTest();
        test.start();

        Thread.sleep(60000);

        test.stop();

        System.out.println("work done!");
//        Long al = 12L;
//        Long bl = null;
//        System.out.println(al.equals(bl));
//        System.out.println(bl instanceof Long);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("do my task...");
    }
}
