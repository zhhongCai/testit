package com.test.it.netty.example;

import java.util.Date;

/**
 * @Author: caizhh
 * @Date: Create in 18-8-2 下午1:46
 * @Description:
 */
public class UnixTime {
    private final long value;

    /*
     * https://tools.ietf.org/html/rfc868
     * 2208988800是1970-01-01 00:00.00距离1900-01-01  00:00.00的秒数
     */
    public static final long TIME_IN_SECONDS = 2208988800L;

    public UnixTime(long value) {
        this.value = value;
    }

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + TIME_IN_SECONDS);
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((getValue() - TIME_IN_SECONDS) * 1000L).toString();
    }
}
