package com.test.it.proxy.jdk;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019/11/12 09:14
 * @Description:
 */
public class ServiceImpl implements IService {
    @Override
    public String doSth(Integer count) {
        return "ServiceImpl doSth " + count;
    }

    @Override
    public void printSth(String str) {
        System.out.println("ServiceImpl printSth:" + str);
    }
}
