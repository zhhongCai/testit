package com.test.it.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019/11/12 09:08
 * @Description:
 */
public class ServiceHandler implements InvocationHandler {

    private Inteface target;

    public ServiceHandler(Inteface iService) {
        this.target = iService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before " + method.getName() + " call");
        Object obj = method.invoke(target, args);
        System.out.println("return=" + obj);
        System.out.println("after " + method.getName() + " call");
        return obj;
    }
}
