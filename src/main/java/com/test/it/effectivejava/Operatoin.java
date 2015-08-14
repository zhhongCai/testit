package com.test.it.effectivejava;

/**
 * chap06 第30条
 * Author: caizh
 * CreateTime: 2015/4/29 16:45
 * Version: 1.0
 */
public enum Operatoin {
    PLUS("+") {
        @Override
        double apply(double left, double right) {
            return left + right;
        }
    },
    MINUS("-") {
        @Override
        double apply(double left, double right) {
            return left - right;
        }
    },
    TIME("*") {
        @Override
        double apply(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/") {
        @Override
        double apply(double left, double right) {
            if(right == 0) {
                throw new IllegalArgumentException("除数不能为0");
            }
            return left / right;
        }
    }
    ;

    abstract double apply(double left, double right);

    private String alias;

    Operatoin(String alias) {
        this. alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
