package com.test.it.guava;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: caizh
 * CreateTime: 2015/3/16 14:18
 * Version: 1.0
 */
public class PreConditionsTest {

    public static void main(String[] args) {
        PreConditionsTest preConditionsTest = new PreConditionsTest();
        preConditionsTest.sum(null, 12);
    }

    public Integer sum(Integer a, Integer b) {
        a = checkNotNull(a, "a 为空");
        b = checkNotNull(a, "a 为空");
        return a + b;
    }
}
