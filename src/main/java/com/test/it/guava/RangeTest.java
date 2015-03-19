package com.test.it.guava;


import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

/**
 * Author: caizh
 * CreateTime: 2015/3/16 16:59
 * Version: 1.0
 */
public class RangeTest {
    public static void main(String[] args) {
        Range<String> range = Ranges.closed("name", "name7");
        System.out.println(range.contains("namf"));
        System.out.println(range.contains("name3"));
        System.out.println(range.contains("name7"));
    }
}
