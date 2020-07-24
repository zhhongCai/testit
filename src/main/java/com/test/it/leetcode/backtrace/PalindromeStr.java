package com.test.it.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/24 21:31
 * @Description:
 */
public class PalindromeStr {

    public List<List<String>> palindromeSubStr(String str) {
        List<List<String>> lists = new ArrayList<>();

        palindromeSubStr(str, 0, new ArrayList<>(str.length()), lists);

        return lists;
    }

    private void palindromeSubStr(String str, int index, List<String> list, List<List<String>> lists) {
        if (index == str.length()) {
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = 1; i < str.length(); i++) {
            if (index + i > str.length()) {
                return;
            }
            String subStr = str.substring(index, index + i);
            if (!isPalindromeSubStr(subStr)) {
                continue;
            }
            list.add(subStr);
            palindromeSubStr(str, index + i, list, lists);
            list.remove(list.size() - 1);
        }
    }

    private boolean isPalindromeSubStr(String subStr) {
        if (subStr.length() == 1) {
            return true;
        }
        int left = 0;
        int right = subStr.length() - 1;
        while (left < right) {
            if (subStr.charAt(left++) != subStr.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeStr palindromeStr = new PalindromeStr();
        List<List<String>> list = palindromeStr.palindromeSubStr("aacabacaa");
        list.forEach(System.out::println);
    }
}
