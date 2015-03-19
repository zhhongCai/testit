package com.test.it.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;

/**
 * Multiset: An extension to Set interface to allow duplicate elements, and provides various utility
 * methods to deal with the occurrences of such elements in a set.
 *
 * Author: caizh
 * CreateTime: 2015/3/19 9:12
 * Version: 1.0
 */
public class MultiSetTest {

    public static void main(String[] args) {
        HashMultiset<String> multiset = HashMultiset.create();
        for(int i = 0; i < 10; i++) {
            multiset.add("element:" + i%3);
        }
        System.out.println(multiset);
        Iterator<String> it = multiset.iterator();
        while(it.hasNext()) {
            String element = it.next();
            System.out.println(element);
        }
        System.out.println(multiset.count("element:0"));

       for(String str : multiset) {
           System.out.println(str);
       }

        for(Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println(entry.getElement() +"--"+ entry.getCount());
        }

        System.out.println("============================");
        for(String d : multiset.elementSet()){
            System.out.println(d);
        }
    }
}
