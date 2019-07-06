package com.test.it.jdktest.jdk8.lang.ref;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * Created by caizh on 2015/10/15 0015.
 */
public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<byte[][], byte[][]> weakHashMap = new WeakHashMap<byte[][], byte[][]>();
        for(int i = 0; i < 1000; i++) {
            weakHashMap.put(new byte[100][100], new byte[100][100]);
            System.gc();
            Thread.sleep(1000L);
            System.err.println(i);
        }
    }

    /**
     * -Xmx1024m -Xms1024m -XX:+PrintGCDetails
     */
    @Test
    public void testSoftReference() {
        int size = 100000000;

        SoftReference<List<String>> res = new SoftReference<>(Lists.newLinkedList());

        for (int i = 0; i < size; i++) {
            List<String> ref = res.get();
            if (ref == null) {
                System.out.println("oom");
                Assert.assertNotNull(ref);
            } else {
                ref.add(UUID.randomUUID().toString());
            }
            ref = null;
        }

        List<String> result = res.get();
        if (result == null) {
            Assert.assertNotNull(res);
        }

        Assert.assertEquals(size, result.size());
    }
}
