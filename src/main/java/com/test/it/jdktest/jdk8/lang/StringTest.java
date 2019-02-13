package com.test.it.jdktest.jdk8.lang;

/**
 * Author: caizh
 * CreateTime: 2015/5/19 14:05
 * Version: 1.0
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowe# per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found on this line." +
                "At most one statement is allowed per line, but 2 statements were found o this line.";
        char a = '#';
        String b = "#";

        long before = System.currentTimeMillis();
        int index = 0;
        for(int i = 0; i < 100000; i++) {
            index = str.indexOf(a);
        }
        long after = System.currentTimeMillis();
        System.out.println("index=" + index + ", cost time: " + (after-before));
        for(int i = 0; i < 100000; i++) {
            index = str.indexOf(b);
        }
        before = System.currentTimeMillis();
        System.out.println("index=" + index + ", cost time: " + (before-after));
    }
}
