package com.test.it.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Author: caizh
 * CreateTime: 2015/3/19 13:59
 * Version: 1.0
 */
public class TableTest {
    public static void main(String[] args) {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("XiaMen", "temperature", 26);
        table.put("FuZhou", "temperature", 28);
        table.put("SanMing", "temperature", 26);
        System.out.println(table);
    }
}
