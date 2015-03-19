package com.test.it.guava;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * Author: caizh
 * CreateTime: 2015/3/16 13:37
 * Version: 1.0
 */
public class OptionalTest {
    @Test
    public void test() {
        Integer a = null;
        Integer b = new Integer(12);
        Optional<Integer> va = Optional.fromNullable(a);
        Optional<Integer> vb = Optional.of(b);

        System.out.println("va isPresent()=" + va.isPresent());
        System.out.println("vb isPresent()=" + vb.isPresent());

        Integer c = va.or(Integer.valueOf(0));
        Integer d = vb.get();

        System.out.println("result: " + (c+d));
    }
}
