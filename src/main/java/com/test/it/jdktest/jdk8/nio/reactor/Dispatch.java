package com.test.it.jdktest.jdk8.nio.reactor;

import java.nio.channels.SelectionKey;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/12 18:16
 * @Description:
 */
public interface Dispatch {

    boolean processSocket(SelectionKey sk);
}
