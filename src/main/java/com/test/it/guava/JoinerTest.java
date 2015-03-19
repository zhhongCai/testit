package com.test.it.guava;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 14:18
 * Version: 1.0
 */
public class JoinerTest {
    public static void main(String[] args) {
        System.out.println(Joiner.on("-==-").join(Arrays.asList(1,2,3,4,5,6,7,8,9)));
    }
}
