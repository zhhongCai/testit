package com.test.it.tomcat.exp20.modelbeantest;

/**
 * Created by caizh on 2015/9/2 0002.
 */
public class Car {
    private String color = "green";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void drive() {
        System.out.println("drive a cat...");
    }
}
