package com.test.it.guava.primitives;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 *
 * Author: caizh
 * CreateTime: 2015/3/19 14:46
 * Version: 1.0
 */
public class BytesTest {
    public static void main(String[] args) {
        byte[] bytes = {1, 2, 3, 4, 5, 6,7, 8, 9};
        List<Byte> byteList =  Bytes.asList(bytes);
        System.out.println(byteList);
        byte[] b = Bytes.toArray(byteList);
        for(byte v : b) {
            System.out.print(v + " ");
        }
    }
}
