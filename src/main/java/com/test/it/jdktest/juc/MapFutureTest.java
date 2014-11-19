package com.test.it.jdktest.juc;

import java.util.concurrent.*;

/**
 * Author: caizh
 * CreateTime: 2014/10/17 8:40
 * Version: 1.0
 */
public class MapFutureTest {
    private ConcurrentMap<String, Future<Long>> result = new ConcurrentHashMap<String, Future<Long>>();

    private Calculate<Long> sum = new Sum();
    private ExecutorService executorService = Executors.newFixedThreadPool(50);

    public Long doCalc(final Long t, final Long t2) throws ExecutionException, InterruptedException {
        String key = t + "_" + t2;
        if(!result.containsKey(key)) {
            Future<Long> f = executorService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    System.out.println("calculate now");
                    return sum.calculate(t, t2);
                }
            });
            result.putIfAbsent(key, f);
        }
        return result.get(key).get();
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final MapFutureTest test = new MapFutureTest();

        for(int j = 0; j < 10; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                for(int i = 0; i < 100; i++) {
                    try {
                        System.out.println(test.doCalc(1234L, 2354L));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                }
            });
            thread.start();
        }

        Thread.sleep(30000);
        test.shutdown();
    }
}

interface Calculate <T> {
    T calculate(T t, T t2);
}

class Sum implements Calculate<Long> {

    @Override
    public Long calculate(Long t, Long t2) {
        return t + t2;
    }
}
