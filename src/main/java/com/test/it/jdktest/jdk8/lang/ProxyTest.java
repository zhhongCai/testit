package com.test.it.jdktest.jdk8.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 * Created by caizh on 2015/8/21.
 */
public class ProxyTest {

    public static void main(String[] args) {
        ObjectTestImpl objectTest = new ObjectTestImpl();
        InvocationHandler handler = new MyInvocationHandler(objectTest);
        ObjectTest test = (ObjectTest) Proxy.newProxyInstance(ObjectTest.class.getClassLoader(), new Class[] {ObjectTest.class}, handler);
        System.out.println(test.doSth("haaaaa"));
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    MyInvocationHandler(Object obj) {
        super();
        this.obj =obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke : ...");
        System.out.println(proxy.getClass().getName());
        Object result = method.invoke(obj, args);
        System.out.println("after invoke : ...");
        return result;
    }
}