package com.test.it.proxy.jdk;

/**
 * @Author: theonecai
 * @Date: Create in 2019/11/12 15:05
 * @Description:
 */
public class ServiceBImpl implements IServiceB {
    @Override
    public String doSth(Integer count) {
        return "ServiceBImpl doSth " + count;
    }

    @Override
    public void show(String str) {
        System.out.println("ServiceBImpl show: " + str);
    }
}
