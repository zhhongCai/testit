package com.test.it.guava.collection;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可修改集合.
 * Author: caizh
 * CreateTime: 2015/6/19 10:11
 * Version: 1.0
 */
public class ImmutableListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        ImmutableList<String> immutableList = ImmutableList.copyOf(list);
        System.out.println(immutableList);

        immutableList.remove(0);
    }
}
