package com.test.it.guava;

import com.google.common.base.CaseFormat;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 14:40
 * Version: 1.0
 */
public class CaseFormatTest {
    public static void main(String[] args) {
        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "HiAmHere"));
    }
}
