package com.test.it.algorithms;

import java.util.Random;

/**
 * 快排
 * @Author: caizhh
 * @Date: Create in 19-4-9 上午 10:01
 * @Description:
 */
public class QuickSort {

    private static Random random = new Random();

    public static void quickSort(int[] a) {

        quickSort(a, 0, a.length);
    }

    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int pos = partition(a, start, end);

        quickSort(a, start, pos);
        //+1是因为pos位置已经第pos大的数字了(有序了)
        quickSort(a, pos + 1, end);
    }

    private static int partition(int[] a, int start, int end) {
        int pivotIndex = end - 1;
        //取末尾元素为分区元素
        int pivot = a[pivotIndex];
        // 当前比pivot小的下标,a[start~lessPos]为比pivot小的元素
        int lessPos = start;
        for (int i = lessPos; i < end - 1; i++) {
            //注意=号，存在相同数字的情况
            if (a[i] <= pivot) {
                swap(a, i, lessPos);
                lessPos++;
            }
        }
        swap(a, lessPos, pivotIndex);
        /*System.out.print("start=" + start + ", end=" + end + ", lessPos=" + lessPos + ": ");
        print(a, start, end);*/
        return lessPos;
    }

    private static void swap(int[] a, int i, int pos) {
        if (i == pos) {
            return;
        }
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

        int b[] = {36, 38, 27, 18, 46, 58, 44, 26, 68, 12};
        QuickSort.quickSort(b);
        print(b);
    }
}
