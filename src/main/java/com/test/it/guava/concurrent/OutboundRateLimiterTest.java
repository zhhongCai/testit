package com.test.it.guava.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: caizhh
 * @Date: Create in 18-10-11 上午11:24
 * @Description:
 */
public class OutboundRateLimiterTest {

    private OutboundRateLimiter outboundRateLimiter;

    private ExecutorService executorService;

    @Before
    public void setUp() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        executorService = new ThreadPoolExecutor(cpuNum + 1, cpuNum * 2 ,
                10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),
                new ThreadPoolExecutor.CallerRunsPolicy());

        outboundRateLimiter = new OutboundRateLimiter();
    }

    @After
    public void tearDown() throws InterruptedException {
        if (executorService != null) {
            executorService.shutdown();

            executorService.awaitTermination(5, TimeUnit.SECONDS);

            executorService.shutdownNow();
        }
    }

    @Test
    public void testAcquire() throws InterruptedException {

        outboundRateLimiter.setPlatformPermitRateList("joom:10");

        String platform = "joom";

        AtomicInteger count = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                long time = System.currentTimeMillis()/1000;
                outboundRateLimiter.acquire(platform);

                System.out.println(time + "=>" + count.incrementAndGet());
            });
        }

        Thread.sleep(10000);
    }

}
