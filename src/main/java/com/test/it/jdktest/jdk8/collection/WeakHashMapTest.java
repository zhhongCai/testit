package com.test.it.jdktest.jdk8.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.WeakHashMap;

/**
 * 参见：https://github.com/eugenp/tutorials.git
 * @Author: theonecai
 * @Date: Create in 2019-07-05 14:44
 * @Description:
 */
public class WeakHashMapTest {

    /**
     * 测试使用WeakHashMap
     * -XX:+PrintGCDetails
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        WeakHashMap<KeyObject, ValueObject> weakHashMap = new WeakHashMap<>();
        KeyObject key = new KeyObject("test");
        KeyObject key2 = new KeyObject("test2");
        weakHashMap.put(key, new ValueObject("value"));
        weakHashMap.put(key2, new ValueObject("value2"));

        Assert.assertTrue(weakHashMap.containsKey(key));
        Assert.assertTrue(weakHashMap.containsKey(key2));

        key = null;

        System.gc();

        Thread.sleep(10000);

        Assert.assertFalse(weakHashMap.containsKey(key));
        Assert.assertTrue(weakHashMap.containsKey(key2));
        Assert.assertEquals(1, weakHashMap.size());
    }
}
