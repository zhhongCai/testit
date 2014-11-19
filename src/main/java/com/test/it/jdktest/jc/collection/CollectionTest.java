package com.test.it.jdktest.jc.collection;

import java.util.*;

/**
 * Author: caizh
 * CreateTime: 2014/10/8 10:29
 * Version: 1.0
 */
public class CollectionTest {
    private Collection collection;
    private AbstractCollection abstractCollection;
    private Set set;
    private List list;

    public void test() {
        collection = new ArrayList();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        Object[] arr = collection.toArray();
        Object[] arr2 = collection.toArray(new String[3]);
        
    }

    public static void main(String[] args) throws InterruptedException {
        new CollectionTest().test();
        Thread.sleep(100000);
    }
}
