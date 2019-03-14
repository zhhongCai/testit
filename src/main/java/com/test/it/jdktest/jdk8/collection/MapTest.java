package com.test.it.jdktest.jdk8.collection;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: caizhh
 * @Date: Create in 19-3-12 下午2:29
 * @Description:
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> emptyMap = Collections.emptyMap();
        emptyMap.get("test");
        emptyMap.forEach((k, v) -> System.out.println(""));

    }
}
