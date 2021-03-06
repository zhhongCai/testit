package com.test.it.algorithms;

/**
 * 字符串匹配算法: KMP算法
 * @Author: theonecai
 * @Date: Create in 2020/6/28 08:48
 * @Description:
 */
public class StringSearchKMP {

    /**
     * next[k]标识0~k最长匹配后缀对应的前置的最后一个字符的位置
     * @param pattern
     * @return
     */
    private static int[] genNext(char[] pattern) {
        int len = pattern.length;
        int k = -1;
        int j = 0;
        int[] next = new int[len];
        next[0] = -1;
        for(j = 1; j < len; j++) {
            while (k > -1 && pattern[k + 1] != pattern[j]) {
                k = next[k];
            }
            if (pattern[j] == pattern[k + 1]) {
                k++;
            }
            next[j] = k;
        }
        ArrayUtil.print(next);
        return next;
    }

    public static int search(String str, String pattern) {
        int[] next = genNext(pattern.toCharArray());

        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = RandomStringUtil.randomString(28);
        String pattern = str.substring(11, 20);
        int p = StringSearchKMP.search(str, pattern);
        System.out.println("source str: " + str);
        System.out.println("pattern   : " + pattern);
        if (p != -1) {
            System.out.println("find position: " + p + ": " + str.substring(0, p) + " " + pattern + " " + str.substring(p + pattern.length()));
        } else {
            System.out.println("notfound");
        }
    }
}
