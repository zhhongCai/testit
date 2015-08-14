package com.test.it.junit;


import org.junit.*;

/**
 * Author: caizh
 * CreateTime: 2015/4/13 10:14
 * Version: 1.0
 */
public class JunitTest {
    @Before
    public void setUp() {
        System.out.println("do some init work");
    }

    @After
    public void tearDown() {
        System.out.println("resource release");
    }

    @Test
    public void test() {
        System.out.println("do sth.");
    }

    @Test
    public void test2() {
        System.out.println("do sthm 2.");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }
}
