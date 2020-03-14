package com.test.it.jdktest.jdk8.nio.reactor;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/13 13:46
 * @Description:
 */
public class MultiThreadDispatch implements Dispatch {
    private ThreadPoolExecutor executor;

    public MultiThreadDispatch(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @Override
    public boolean processSocket(SelectionKey sk) {
        executor.execute(new SocketProcessor(sk));
        return true;
    }
}
