package com.test.it.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/22 21:15
 * @Description:
 */
public class WordPlusEq {
    private boolean equals;
    private long sumLeft;
    private long sum;

    public boolean result(String[] words, String result) {
        equals = false;
        sumLeft = 0;
        sum = 0;

        calculate(words, result);

        return equals;
    }

    private void calculate(String[] words, String result) {
        if (equals) {
            return;
        }

        Map<Character, Integer> charMap = new HashMap<>(10);
        boolean[] used = new boolean[10];

        for (int i = 0; i < 10; i++) {

        }

        for (String word : words) {
            for (int i = 0; i < word.toCharArray().length; i++) {
                char ch = word.charAt(i);
                if (!charMap.containsKey(ch)) {
                    getUnusedVal(charMap, used, i == 0, ch);
                }
                sumLeft += charMap.get(ch) * getBase(i, word.length());
            }
        }

        for (int i = 0; i < result.toCharArray().length; i++) {
            char ch = result.charAt(i);
            if (!charMap.containsKey(ch)) {
                getUnusedVal(charMap, used, i == 0, ch);
            }
            sum += charMap.get(ch) * getBase(i, result.length());
        }

    }

    private void getUnusedVal(Map<Character, Integer> charMap, boolean[] used, boolean firstChar, char ch) {
        int j = firstChar ? 1 : 0;
        for (; j < 10; j++) {
            if (!used[j]) {
                charMap.put(ch, j);
                used[j] = true;
                break;
            }
        }
    }

    private long getBase(int i, int length) {
        int k = 1;
        long val = 1;
        while (k < length - i) {
            val *= 10;
            k++;
        }
        return val;
    }

    public static void main(String[] args) {
        /**
         * ABC DEFG = EDFD
         */
    }
}
