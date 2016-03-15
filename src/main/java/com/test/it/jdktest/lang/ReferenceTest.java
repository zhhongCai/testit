package com.test.it.jdktest.lang;

import java.util.WeakHashMap;

/**
 * Created by caizh on 2015/10/15 0015.
 */
public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<byte[][], byte[][]> weakHashMap = new WeakHashMap<byte[][], byte[][]>();
        for(int i = 0; i < 1000; i++) {
            weakHashMap.put(new byte[100][100], new byte[100][100]);
            System.gc();
        Thread.sleep(1000L);
            System.err.println(i);
        }
    }
}
