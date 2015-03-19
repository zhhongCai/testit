package com.test.it.guava;

import com.google.common.base.Splitter;

import java.util.Iterator;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 14:23
 * Version: 1.0
 */
public class SplitterTest {
    public static void main(String[] args) {
        Iterator<String> it = Splitter.on("-")
                .trimResults()
                .omitEmptyStrings()
                .split("sn-num-ber-it- -test ").iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
