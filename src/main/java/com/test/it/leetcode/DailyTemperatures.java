package com.test.it.leetcode;

/**
 * leetcode 739
 * @Author: theonecai
 * @Date: Create in 2020/6/20 22:01
 * @Description:
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return new int[0];
        }

        int len = T.length;
        int[] result = new int[len];
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = i + 1; j < len; j++) {
                if (T[i] < T[j]) {
                    break;
                }
            }
            if (j == len) {
                result[i] = 0;
            } else {
                result[i] = j - i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};

        DailyTemperatures dt = new DailyTemperatures();
        int[] result = dt.dailyTemperatures(t);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }
    }
}
