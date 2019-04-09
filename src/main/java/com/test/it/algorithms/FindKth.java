package com.test.it.algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 查找无序数组中第K大的数
 * @Author: caizh
 * @Date: Create in 2019-04-09 14:33
 * @Description:
 */
public class FindKth {
    public static int findKth(int[] array, int k) {
        int p = partition(array, 0, array.length);

        while(p + 1 != k) {
            if (p + 1 > k) {
                p = partition(array, 0, p);
            } else {
                p = partition(array, p + 1, array.length);
            }
        }
        return array[p];
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end - 1];
        int lessPos = start;
        for (int i = start; i < end - 1; i++) {
            if (array[i] <= pivot) {
                swap(array, i, lessPos++);
            }
        }
        swap(array, lessPos, end - 1);
        return lessPos;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
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
        int[] b = Arrays.copyOf(a, len);

        QuickSort.quickSort(a);
        System.out.println("quick sort:");
        print(a);

        MergeSort.mergeSort(b);
        System.out.println("merge sort:");
        print(b);

        int k = random.nextInt(len) % 10 + 1;
        System.out.print("the " + k + "th is ");
        System.out.println(FindKth.findKth(a, k));
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
}
