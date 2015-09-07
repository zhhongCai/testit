package com.test.it.rmi.server;

import java.io.Serializable;

/**
 * Created by caizh on 2015/8/24 0024.
 */
public class Account implements Serializable,Cloneable{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
