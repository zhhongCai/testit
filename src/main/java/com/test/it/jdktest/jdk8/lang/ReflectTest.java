package com.test.it.jdktest.jdk8.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Author: caizh
 * CreateTime: 2015/5/21 15:04
 * Version: 1.0
 */
public class ReflectTest {

    long longTime;

    String doSth(List list) {
        return "";
    }

    public static void main(String[] args) {
        Field[] fields = IDoSth.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
        }
        Method[] methods = IDoSth.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + method.getParameterTypes());
        }
        System.out.println("=============================================");
        Field[] fields2 = ReflectTest.class.getDeclaredFields();
        for (Field field : fields2) {
            System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
        }
        Method[] methods2 = ReflectTest.class.getDeclaredMethods();
        for (Method method : methods2) {
            System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + method.getParameterTypes());
        }
    }
}
