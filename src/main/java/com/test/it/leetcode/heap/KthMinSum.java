package com.test.it.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/14 21:16
 * @Description:
 */
public class KthMinSum {

    public int kthMinSun(int[][] nums, int k) {
        int row = nums.length;
        int col = nums[0].length;
        int[] visitedIndex = new int[row];
        PriorityQueue<Node> minHeap = new PriorityQueue<>(row);
        int val = 0;
        for (int i = 0; i < row; i++) {
            minHeap.add(new Node(i, 1, nums[i][1]));
            val += nums[i][0];
        }
        int count = 1;
        Node top = null;
        while (count < k) {
            top = minHeap.poll();
            count++;
        }

        return val;
    }

    static class Node implements Comparable<TrapRainWater.Node> {
        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getVal() {
            return val;
        }

        @Override
        public int compareTo(TrapRainWater.Node o) {
            return this.val - o.height;
        }
    }

    public static void main(String[] args) {
        KthMinSum kthMinSum = new KthMinSum();
        int[][] nums = {
                {1, 2, 3, 3, 6},
                {1, 1, 3, 4, 7},
                {1, 2, 3, 4, 8},
        };
        kthMinSum.kthMinSun(nums, 10);
    }
}
