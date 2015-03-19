package com.test.it.guava.collection;

import com.google.common.collect.HashMultimap;
import org.junit.Test;

import java.util.Map;

/**
 * Author: caizh
 * CreateTime: 2015/1/15 14:57
 * Version: 1.0
 */
public class MultimapTest {

    @Test
    public void testMultimap() {
        HashMultimap<String, String> info = HashMultimap.create(4, 3);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                info.put(i + "", i + "-" + j);
            }
        }
        for(Map.Entry<String, String> entity : info.entries()) {
            System.out.println(entity.getKey() + "=" + entity.getValue());
        }
    }
}
