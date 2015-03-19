package com.test.it.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 11:39
 * Version: 1.0
 */
public class BiMapTest {
    public static void main(String[] args) {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("test", "v1");
        biMap.put("test", "v2");
        biMap.put("k3", "v1");
        System.out.println(biMap);
    }
}
