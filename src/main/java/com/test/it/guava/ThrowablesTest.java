package com.test.it.guava;

import com.google.common.base.Throwables;

import javax.transaction.NotSupportedException;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 8:59
 * Version: 1.0
 */
public class ThrowablesTest {

    public void doSth(int num) throws NotSupportedException {
        if(num < 0) {
            throw new NotSupportedException("num is negative");
        }
    }

    public static void main(String[] args) throws NotSupportedException {
        ThrowablesTest throwablesTest = new ThrowablesTest();
        try {
            throwablesTest.doSth(-1);
        } catch (NotSupportedException e) {
//            Throwables.propagateIfInstanceOf(e, NotSupportedException.class);
            Throwables.propagate(e);
//            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }
}
