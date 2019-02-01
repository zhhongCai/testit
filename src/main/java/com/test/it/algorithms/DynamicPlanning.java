package com.test.it.algorithms;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 动态规划
 * @Author: caizhh
 * @Date: Create in 19-1-30 下午1:33
 * @Description:
 */
public class DynamicPlanning {

    public static void main(String[] args) {
        // 结果放到 maxW 中
        int maxW = Integer.MIN_VALUE;
        // 物品重量
        int[] weight = {2,2,4,6,3};
        // 物品个数
        int n = 5;
        // 背包承受的最大重量
        int w = 9;

        System.out.println("\ntotalWeight=" + dp(weight, n, w));
    }

    /**
     * 0-1背包
     * @param weight: 物品重量
     * @param n: 物品个数
     * @param w: 背包可承载重
     */
    private static int dp(int[] weight, int n, int w) {
        //state[i][w]
        boolean[][] state = new boolean[n][w+1];
        state[0][0] = true;
        state[0][weight[0]] = true;

        for (int i = 1; i < n; i++) {
            //i不放入背包
            for (int j = 0; j < w; j++) {
                if (state[i-1][j]) {
                    state[i][j] = true;
                }
            }
            //i放入背包
            for (int j = 0; j <= w-weight[i]; j++) {
                if (state[i-1][j]) {
                    state[i][j + weight[i]] = true;
                }
            }
        }

        int maxW = 0;
        for (int i = w; i > 0; i--) {
            if (state[n-1][i]) {
                maxW = i;
                break;
            }
        }

        int i = n-1;
        int j = maxW;
        List<Integer> picked = Lists.newArrayList();
        while(j > 0 && i > 0) {
            if (state[i][j]) {
                j -= weight[i];
                picked.add(weight[i]);
                i--;
            }
        }
        Lists.reverse(picked).forEach(pw -> System.out.print(pw + " "));

        return maxW;
    }


}
