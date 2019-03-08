package com.test.it.guava.common.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;

import java.nio.charset.Charset;

/**
 * @Author: caizhh
 * @Date: Create in 19-2-20 下午1:59
 * @Description:
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<String> stringBloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000, 0.001);
        for (int i = 0; i < 1000; i++) {
            stringBloomFilter.put(i + "fltest");
        }

        Assert.assertTrue(stringBloomFilter.mightContain("1fltest"));
        Assert.assertTrue(stringBloomFilter.mightContain("10fltest"));
        Assert.assertTrue(stringBloomFilter.mightContain("12fltest"));
        Assert.assertTrue(stringBloomFilter.mightContain("21fltest"));
        Assert.assertTrue(stringBloomFilter.mightContain("32fltest"));
        Assert.assertTrue(stringBloomFilter.mightContain("100fltest"));
        Assert.assertFalse(stringBloomFilter.mightContain("1111fltest"));

    }
}
