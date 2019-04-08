package com.test.it.algorithms;

import java.util.Random;

public class QuickSort {

    public static void quickSort(int[] a) {

        quickSort(a, 0, a.length);
    }

    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int pos = partition(a, start, end);

        if (pos == start) {
            return;
        }

        quickSort(a, start, pos);
        quickSort(a, pos, end);
    }

    private static int partition(int[] a, int start, int end) {
        //取末尾元素为分区元素
        int pivot = a[end - 1];
        // 当前比pivot小的下标,a[start~lessPos]为比pivot小的元素
        int lessPos = start;
        for (int i = lessPos; i < end - 1; i++) {
            //注意=号，存在相同数字的情况
            if (a[i] <= pivot) {
                swap(a, i, lessPos);
                lessPos++;
            }
        }
        swap(a, lessPos, end - 1);
        System.out.print("start=" + start + ", end=" + end + ", lessPos=" + lessPos + ": ");
        print(a, start, end);
        return lessPos;
    }

    private static void swap(int[] a, int i, int pos) {
        int t = a[pos];
        a[pos] = a[i];
        a[i] = t;
    }

    private static void print(int[] a) {
        print(a, 0, a.length);
    }

    private static void print(int[] a, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int[] a = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            a[i] = random.nextInt(100);
        }
        System.out.println("origin:");
        print(a);

        QuickSort.quickSort(a);

        System.out.println("result:");
        print(a);
    }
}
