package com.test.it.algorithms;

/**
 * 字符串匹配算法: KMP算法
 * @Author: theonecai
 * @Date: Create in 2020/6/28 08:48
 * @Description:
 */
public class StringSearchKMP {

    private int[] genNext(char[] pattern) {
        int len = pattern.length;
        int k = -1;
        int j = 0;
        int[] next = new int[len];
        next[0] = -1;
        for(j = 0; j < len; j++) {
            while (k > -1 || pattern[k + 1] != pattern[j]) {
                k = next[k];
            }
            if (pattern[j] != pattern[k + 1]) {
                k++;
            }
            next[j] = k;
        }
        ArrayUtil.print(next);
        return next;
    }

    public int search(String str, String pattern) {
        int[] next = genNext(pattern.toCharArray());

        for (int i = 0; i < str.length() - pattern.length(); i++) {
        }
        return -1;
    }
}
