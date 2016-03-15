package com.test.it.jdktest.lang;

import java.io.IOException;

/**
 * Created by caizh on 2015/8/14.
 */
public class RuntimeTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("runtime.availableProcessors()=" + runtime.availableProcessors());
        System.out.println("runtime.freeMemory()=" + runtime.freeMemory()/1024/1024);
        System.out.println("runtime.maxMemory()=" + runtime.maxMemory()/1024/1024);
        System.out.println("runtime.totalMemory()=" + runtime.totalMemory()/1024/1024);

        try {
            runtime.exec("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SecurityManager securityManager = System.getSecurityManager();
    }
}
