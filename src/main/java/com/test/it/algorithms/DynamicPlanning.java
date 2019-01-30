package com.test.it.algorithms;

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

        int w = 9; // 背包承受的最大重量

        dp(weight, n, w);
    }

    /**
     * 0-1背包
     * @param weight: 物品重量
     * @param n: 物品个数
     * @param w: 背包可承载重
     */
    private static void dp(int[] weight, int n, int w) {
        boolean[][] state = new boolean[n][w+1];
        state[0][0] = true;
        state[0][weight[0]] = true;

        for (int i = 0; i < n; i++) {
            //i不放入背包
            for (int j = 0; j < j; j++) {
                if (state[i-1][j]) {
                    state[i][j] = true;
                }
            }
            //i放入背包
            for (int j = 0; j < j; j++) {
                if (state[i-1][j]) {
                    state[i][j + weight[i]] = true;
                }
            }
        }

        for (int i = w; i > 0; i--) {
            if (state[n-1][i]) {
                System.out.println(i);
                return;
            }
        }
    }


}
