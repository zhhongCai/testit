package com.test.it.algorithms;

import java.util.Random;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/18 09:35
 * @Description:
 */
public class ArrayUtil {
    public static int[] randArray(int size) {
        int[] a = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(size*3);
        }
        return a;
    }

    public static int[] randArray() {
        return randArray(new Random().nextInt(100000) + 1);
    }

    public static void print(int[] a) {
        print(a, 0, a.length);
    }

    public static void print(int[] a, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
