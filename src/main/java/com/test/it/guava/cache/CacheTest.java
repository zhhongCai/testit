package com.test.it.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 14:08
 * Version: 1.0
 */
public class CacheTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10240)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("费时调用");
                        return "now:" + System.currentTimeMillis();
                    }
                });

        cache.put("now", "now:" + System.currentTimeMillis());
        System.out.println(cache.get("now"));
        System.out.println(cache.get("now"));
        System.out.println(cache.get("now"));
        Thread.sleep(11000L);
        System.out.println(cache.get("now"));
    }
}
