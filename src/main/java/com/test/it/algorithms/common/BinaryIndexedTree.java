package com.test.it.algorithms.common;

import com.test.it.algorithms.ArrayUtil;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/10 20:12
 * @Description:
 */
public class BinaryIndexedTree {
    int[] arr;
    int[] sum;

    public BinaryIndexedTree(int[] arr) {
        this.arr = arr;
        this.sum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            int x = i;
            while (x > 0) {
                sum[i] += arr[x - 1];
                x -= lowbit(x);
            }
        }
    }

    private int lowbit(int num) {
        return num & (-num);
    }

    public int getSum(int x) {
        int i = x;
        int s = 0;
        while (i > 0) {
            s += sum[i];
            i -= lowbit(i);
        }
        return s;
    }

    public int getSum(int x, int y) {
        return getSum(y) - getSum(x);
    }

    public void update(int x, int addVal) {
        arr[x] = arr[x] + addVal;
        int i = x;
        while (i < sum.length) {
            sum[i] += addVal;
            i += lowbit(x);
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.randIntArray(10);
        BinaryIndexedTree biTree = new BinaryIndexedTree(arr);

        ArrayUtil.print(biTree.arr);
        ArrayUtil.print(biTree.sum);
    }
}
