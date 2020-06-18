package com.test.it.algorithms;

import java.util.Arrays;

import static com.test.it.algorithms.ArrayUtil.print;

/**
 * 查找无序数组中第K大的数
 * @Author: caizh
 * @Date: Create in 2019-04-09 18:33
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
        int len = 100;
        int[] a = ArrayUtil.randArray(len);
        System.out.println("origin:");
        print(a);
        int[] b = Arrays.copyOf(a, len);
        int[] c = Arrays.copyOf(a, len);

        int k = 10;
        System.out.print("the " + k + "th is ");
        System.out.println(FindKth.findKth(a, k));

        QuickSort.quickSort(b);
        System.out.println("quick sort:");
        print(b);

        MergeSort.mergeSort(c);
        System.out.println("merge sort:");
        print(c);
    }

}
