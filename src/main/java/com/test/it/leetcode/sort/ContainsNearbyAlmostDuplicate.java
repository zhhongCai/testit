package com.test.it.leetcode.sort;

import com.test.it.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 220
 * @Author: theonecai
 * @Date: Create in 2020/8/26 20:46
 * @Description:
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0 || nums == null || nums.length < 1) {
            return false;
        }
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        Arrays.sort(nodes);

        long tmp;
        for (int i = 0; i < nodes.length; i++ ) {
            for (int j = i - 1; j >= 0; j--) {
                tmp = Math.abs((long)nodes[i].val - (long)nodes[j].val);
                if (tmp <= (long)t &&
                        Math.abs(nodes[j].index - nodes[i].index) <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    static class Node implements Comparable<Node> {
        int val;
        int index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }


    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate containsNearbyAlmostDuplicate = new ContainsNearbyAlmostDuplicate();
        int[] nums = ArrayUtil.randIntArray(11);
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums, 2, 2));
        int[] nums2 = {1,5,9,1,5,9};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums2, 3, 2));
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums2, 4, 2));
        int[] nums3 = {1,3,6,2};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums3, 1, 2));
        int[] nums4 = {-1,-1};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums4, 1, 0));
        /**
         * [-1,2147483647]
         * 1
         * 2147483647
         */
        int[] nums5 = {-1,2147483647};
        Assert.assertFalse(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums5, 1, 2147483647));

    }
}
