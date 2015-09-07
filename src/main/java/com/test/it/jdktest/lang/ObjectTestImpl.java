package com.test.it.jdktest.lang;

/**
 * Created by caizh on 2015/8/21.
 */
public class ObjectTestImpl implements ObjectTest {
    @Override
    public String doSth(String sth) {
        System.out.println("doSth: " + sth);
        return "done...";
    }
}
