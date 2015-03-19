package com.test.it.guava.math;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 14:56
 * Version: 1.0
 */
public class IntMathTest {
    public static void main(String[] args) {
        System.out.println(IntMath.divide(13, 3, RoundingMode.CEILING));
    }
}
