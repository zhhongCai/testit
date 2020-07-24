package com.test.it.leetcode.sort;

import com.test.it.algorithms.ArrayUtil;

/**
 * leetcode 51
 * @Author: theonecai
 * @Date: Create in 2020/7/7 20:41
 * @Description:
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <2) {
            return 0;
        }

        return mergeSortCounting(nums, 0, nums.length - 1);
    }

    /**
     * 合并排序方法统计
     * @param arr
     * @param start
     * @param end : 包括end
     * @return
     */
    private int mergeSortCounting(int[] arr, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (end + start) / 2;  // equals start + (end - start) / 2
        int sum = 0;
        sum += mergeSortCounting(arr, start, mid);
        sum += mergeSortCounting(arr, mid + 1, end);

        sum += merge(arr, start, mid, end);
        return sum;
    }

    private int merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int index = 0;
        int[] sortedArr = new int[end - start + 1];
        int count = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                sortedArr[index++] = arr[i++];
            } else {
                count += mid - i + 1;
                sortedArr[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            sortedArr[index++] = arr[i++];
        }
        while (j <= end) {
            sortedArr[index++] = arr[j++];
        }

        for (i = 0; i < sortedArr.length; i++) {
            arr[start + i] = sortedArr[i];
        }
        return count;
    }

    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] arr = ArrayUtil.randIntArray(5);
        ArrayUtil.print(arr);
        System.out.println("disorder: " + rp.reversePairs(arr));
        ArrayUtil.print(arr);
        int[] b = {7,5,6,4};
        System.out.println("disorder: " + rp.reversePairs(b));
    }

}
