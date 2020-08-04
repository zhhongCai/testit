package com.test.it.leetcode.stack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/30 21:43
 * @Description:
 */
public class MaxRectangleMatrix {

    public int maximalRectangle(char[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        List<int[]> heights = new ArrayList<>(m.length);
        for (int col = 0; col < m[0].length; col++) {
            int[] height = new int[m.length];
            for (int row = 0; row < m.length; row++) {
                if (m[row][col] == '0') {
                    height[row] = 0;
                } else {
                    if (col > 0 && m[row][col - 1] == '1') {
                        height[row] = heights.get(col - 1)[row] - 1;
                        continue;
                    }
                    int colIndex = col + 1;
                    while (colIndex < m[0].length && m[row][colIndex] == '1') {
                        colIndex++;
                    }
                    height[row] = colIndex - col;
                }
            }
            heights.add(height);
        }
        int max = 0;
        for (int i = 0; i < m[0].length; i++) {
            max = Math.max(max, largestRectangleArea(heights.get(i)));
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        // 递增栈,存索引
        Stack<Integer> incStack = new Stack<>();
        int popIndex;
        int maxRange;
        int max = heights[0];
        incStack.push(0);

        int i = 1;
        while (!incStack.isEmpty()) {
            while ((i < heights.length && heights[incStack.peek()] > heights[i]) || i == heights.length) {
                popIndex = incStack.pop();
                if (heights[popIndex] > 0) {
                    maxRange = incStack.isEmpty() ? i : (i - incStack.peek() - 1);
                    max = Math.max(max, heights[popIndex] * maxRange);
                }
                if (incStack.isEmpty()) {
                    break;
                }
            }
            if (i < heights.length) {
                incStack.push(i++);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        MaxRectangleMatrix maxRectangleMatrix = new MaxRectangleMatrix();
        char[][] m = {
            {'1', '1', '1', '1', '0', '0'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1'}
        };
        Assert.assertEquals(20, maxRectangleMatrix.maximalRectangle(m));
    }
}
