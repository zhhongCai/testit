package com.test.it.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: caizhh
 * @Date: Create in 18-12-10 上午11:34
 * @Description:
 */
public class HashedWheelTimerTest {

    private HashedWheelTimer timer = new HashedWheelTimer();

    @Test
    public void test() throws InterruptedException {
        System.out.println("starting");
        TimerTask task = timeout -> {
            System.out.println("time task...");
        };
        timer.newTimeout(task, 1000, TimeUnit.MICROSECONDS);
        timer.start();
        Thread.sleep(2000L);
        System.out.println("done");
    }
}
