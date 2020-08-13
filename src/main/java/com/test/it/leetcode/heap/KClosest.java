package com.test.it.leetcode.heap;

import com.test.it.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.PriorityQueue;

/**
 *
 * leetcodd 973
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/13 20:55
 * @Description:
 */
public class KClosest {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> b[2] - a[2]);

        for (int i = 0; i < points.length; i++) {
            if (i < K) {
                int[] tmp = {points[i][0], points[i][1], powAndSum(points[i][0], points[i][1])};
                maxHeap.add(tmp);
            } else {
                int sum = powAndSum(points[i][0], points[i][1]);
                if (sum < maxHeap.peek()[2]) {
                    maxHeap.poll();
                    int[] tmp = {points[i][0], points[i][1],  sum};
                    maxHeap.add(tmp);
                }
            }
        }

        int[][] result = new int[K][2];
        int i = 0;
        int[] tmp;
        while (!maxHeap.isEmpty()) {
            tmp = maxHeap.poll();
            result[i][0] = tmp[0];
            result[i][1] = tmp[1];
            i++;
        }
        return result;
    }

    public int powAndSum(int a, int b) {
        return a * a + b * b;
    }

    public static void main(String[] args) {
        KClosest kClosest = new KClosest();
        int[][] points = {
                {1, 3},
                {-2, 2}
        };
        int[][] result = kClosest.kClosest(points, 2);
        for (int[] ints : result) {
            ArrayUtil.print(ints);
        }
    }
}
