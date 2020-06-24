package com.test.it.algorithms;

/**
 *  -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+LogCompilation -XX:PrintAssemblyOptions=intel
 * @Author: theonecai
 * @Date: Create in 2020/6/17 13:33
 * @Description:
 */
public class Sorts {

    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(Integer[] a) {
        int size = a.length;
        int i, j;
        for (i = size - 1; i >= 0; i--) {
            boolean swapFlag = false;
            for (j = i - 1; j >= 0; j--) {
                if (a[i] < a[j]) {
                    swap(a, i, j);
                    swapFlag = true;
                }
            }
            ArrayUtil.print(a);
            if (!swapFlag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 分为两部分：已排序部分,未排序部分
     * 将未排序部分的每个元素，插入到已排序部分的适当位置
     * @param a
     */
    public static void insertSort(Integer[] a) {
        int size = a.length;
        int i, j, currentVal;
        for (i = 1; i < size; i++) {
            currentVal = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (currentVal < a[j]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * 分为两部分：已排序部分,未排序部分
     * 从未排序部分找出最小值，放到已排序部分的尾部
     */
    public static void selectSort(Integer[] a) {
        int size = a.length;
        int i, j, minIndex;
        for (i = 0; i < size; i++) {
            minIndex = i;
            for (j = i + 1; j < size; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(a, i, minIndex);
            }
        }
    }

    private static void swap(Integer[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int len = 15;
        Integer[] a = ArrayUtil.randArray(len);
        System.out.println("origin:");
        ArrayUtil.print(a);

        Sorts.selectSort(a);

        System.out.println("result:");
        ArrayUtil.print(a);

        Integer b[] = ArrayUtil.randArray(len + 1);
        ArrayUtil.print(b);
        Sorts.selectSort(b);
        ArrayUtil.print(b);
    }
}
