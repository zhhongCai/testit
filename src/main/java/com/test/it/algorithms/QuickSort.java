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

        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

//        int pos = partition(a, start, end);
        int pos = partition2(a, start, end);

        quickSort(a, start, pos - 1);
        //+1是因为pos位置已经第pos大的数字了(有序了)
        quickSort(a, pos + 1, end);
    }

    private static int partition(int[] a, int start, int end) {
        //取首元素为分区元素
        int pivot = a[start];
        // 从左往右当前比pivot大的下标,a[start~low]为比pivot小的元素
        int low = start;
        // 从右往左当前比pivot小的下标,a[hight+1~end]为比pivot大的元素
        int high = end;

        // 注意先从右往左找，后从左往右找：这样当low == high时，保证a[low]小于pivot，此处即为pivot排序后位置
        while (low < high) {
            while (high > low && a[high] >= pivot) {
                high--;
            }
            while (low < high && a[low] <= pivot) {
                low++;
            }
            if (low < high) {
                swap(a, low, high);
            }
        }
        if (low != start) {
            swap(a, low, start);
        }
        System.out.print("start=" + start + ", end=" + end + ", low=" + low + ",hight=" + high + ", pivot=" + pivot + ": ");
        print(a);
        return low;
    }

    private static int partition2(int[] a, int start, int end) {
        //取首元素为分区元素
        int pivotIndex = start;
        int pivot = a[pivotIndex];
        // 从左往右当前比pivot大(等于，注意相等时也需要交换)的下标,a[start~low]为比pivot小的元素
        int low = start;
        // 从右往左当前比pivot小的下标,a[hight~end]为比pivot大的元素
        int high = end;

        //注意先从左往右，后从右往左
        while (low < high) {
            while (low < high && a[low] < pivot) {
                low++;
            }
            while (high > low && a[high] >= pivot) {
                high--;
            }
            if (low < high) {
                if (low == pivotIndex) {
                    pivotIndex = high;
                }
                swap(a, low, high);
            }
        }
        if (low != pivotIndex) {
            swap(a, low, pivotIndex);
        }
        System.out.print("start=" + start + ", end=" + end + ", low=" + low + ",hight=" + high + ", pivot=" + pivot + ": ");
        print(a);
        return low;
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
        int len = 15;
        int[] a = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            a[i] = random.nextInt(len*3);
        }
        System.out.println("origin:");
        print(a);

        QuickSort.quickSort(a);

        System.out.println("result:");
        print(a);

        int b[] = {36, 38, 27, 18, 46, 58, 44, 26, 68, 12};
        print(b);
        QuickSort.quickSort(b);
        print(b);
    }
}
