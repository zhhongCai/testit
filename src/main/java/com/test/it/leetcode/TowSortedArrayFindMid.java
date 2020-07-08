package com.test.it.leetcode;

/**
 * leetcode 4
 * @Author: theonecai
 * @Date: Create in 2020/7/7 19:40
 * @Description:
 */
public class TowSortedArrayFindMid {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }

        if (nums1 == null || (nums1.length > 0 && nums2 != null
                && nums2.length > 0 && nums1[nums1.length - 1] <= nums2[0])) {
            return findMedian(nums2);
        }
        if (nums2 == null || (nums2.length > 0 && nums1.length > 0 && nums2[nums2.length - 1] <= nums1[0])) {
            return findMedian(nums1);
        }

        int total = nums1.length + nums2.length;
        int mid = total / 2;

        int mid1 = nums1.length / 2;
        int mid2 = nums2.length / 2;

        return 0;
    }

    private double findMedian(int[] nums) {
        int total = nums.length;
        int mid = total / 2;

        if (total % 2 == 1 || total < 2) {
            return nums[mid];
        }
        return (nums[mid - 1] + nums[mid]) / 2.0;
    }
}
