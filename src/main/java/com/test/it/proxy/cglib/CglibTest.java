package com.test.it.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Author: caizhh
 * @Date: Create in 19-3-8 下午2:36
 * @Description:
 */
public class CglibTest {
    public static void main(String[] args) {
        //输出代理类class
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/caizh/github/testit/");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println(method.toGenericString());
            System.out.println(args1);
            System.out.println(proxy);
            return proxy.invokeSuper(obj, args1);
        });

        Person p = (Person) enhancer.create();
        p.setAge(12);
        p.setName("test");
        System.out.println(p.toString());
        System.out.println(p.getName() + ",age " + p.getAge());
    }
}
