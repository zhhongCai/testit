package com.test.it.guava.concurrent;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

/**
 * @Author: caizhh
 * @Date: Create in 18-10-11 上午9:36
 * @Description:
 */
public class OutboundRateLimiter {

    private static Logger logger = LoggerFactory.getLogger(OutboundRateLimiter.class);

    /**
     * 平台限速配置，格式如： joom:10,amazon:20
     */
    private static String platformPermitRateList = "joom:100,amazon:5,mail.ru:10,yandex:6";

    private static Map<String, RateLimiter> platformRateLimiterMap = Maps.newConcurrentMap();

    public void setPlatformPermitRateList(String platformPermitRateList) {
        OutboundRateLimiter.platformPermitRateList = platformPermitRateList;

        init();
    }


    private synchronized static void init() {
        platformRateLimiterMap.clear();

        try {
            Splitter.on(",").trimResults().split(platformPermitRateList).forEach(platformPermitRate -> {
                String[] tmp = platformPermitRate.split(":");
                platformRateLimiterMap.put(tmp[0], RateLimiter.create(Double.parseDouble(tmp[1])));
            });
        } catch (Exception e) {
            logger.error("初始平台限速失败:" + e.getMessage(), e);
        }
    }

    public void acquire(String platform) {
        Optional.ofNullable(platformRateLimiterMap.get(platform)).ifPresent(r -> System.out.println("acquire return = " + r.acquire()));
    }
}
