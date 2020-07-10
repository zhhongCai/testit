package com.test.it.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  22
 * @Author: theonecai
 * @Date: Create in 2020/7/10 20:27
 * @Description:
 */
public class Brackets {

    private char[] str;

    List<String> result;

    public List<String> count(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }

        result = new LinkedList<>();
        str = new char[n * 2];
        // ()
        // (()) ()()
        // ((())), (())(),(()()),()(()),()()()
        //f(n*2) =
        f(0, n, '(');

        return result;
    }

    public void f(int currentIndex, int leftCount, char ch) {
        str[currentIndex] = ch;
        if (currentIndex == str.length - 1) {
            if (!isOk(currentIndex)) {
                if (ch == '(') {
                    if (leftCount > 0) {
                        leftCount--;
                    }
                    f(currentIndex, leftCount, ')');
                } else {
                    if (leftCount > 0) {
                        leftCount++;
                    }
                    f(currentIndex, leftCount, '(');
                }
            } else {
                result.add(String.valueOf(str));
            }
            return;
        }
        if (leftCount < 1) {
            f(currentIndex + 1, leftCount - 1, ')');
        } else {
            f(currentIndex + 1, leftCount - 1, '(');
        }
    }

    private boolean isOk(int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
        }
        return true;
    }

    public static void main(String[] args) {
        Brackets brackets = new Brackets();
        List<String> list = brackets.count(3);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
