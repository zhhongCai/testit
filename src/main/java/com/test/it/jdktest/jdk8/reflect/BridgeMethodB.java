package com.test.it.jdktest.jdk8.reflect;

import java.lang.reflect.Method;

/**
 * bridgeMethod
 *
 * @Author: zhenghong.cai
 * @Date: Create in 2019-07-08 11:00
 * @Description:
 */
public class BridgeMethodB extends BridgeMethodA<String> {

    @Override
    String get(String d) {
        return d;
    }

    public static void main(String[] args) {
        Class<BridgeMethodB> clazz = BridgeMethodB.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            printMethod(method);
        }
    }

    private static void printMethod(Method method) {
        System.out.println(method.getReturnType().getSimpleName() + " " + method.getName() + "(" +
                getParameterTyes(method.getParameterTypes()) + ")" + " is bridgeMethod=" + method.isBridge());
    }

    private static String getParameterTyes(Class<?>[] parameterTypes) {
        if (parameterTypes == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Class<?> parameterType : parameterTypes) {
            sb.append(parameterType.getSimpleName()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
}
