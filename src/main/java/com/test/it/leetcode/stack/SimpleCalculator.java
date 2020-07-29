package com.test.it.leetcode.stack;

import org.junit.Assert;

/**
 * leetcode 224
 *
 * @Author: thonecai
 * @Date: Create in 2020/7/29 21:00
 * @Description:
 */
public class SimpleCalculator {

    public int calculate(String s) {

        return 0;
    }

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        /**
         * + +
         * 4 3  2  1 + -  1 ++
         * + + 1 - + 1 2 3 4
         */
        Assert.assertEquals(5, calculator.calculate(" 1 + (1 + 2 - 3) + 4"));
    }
}
