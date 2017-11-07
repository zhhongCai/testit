package com.test.it.mongodb.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

/**
 * Created by caizhh on 2017/11/2.
 */
public class User {

    @Id
    private BigInteger id;

}
