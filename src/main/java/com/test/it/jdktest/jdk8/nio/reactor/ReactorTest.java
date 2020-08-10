package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;

/**
 * @Author: theonecai
 * @Date: Create in 2020/3/13 16:27
 * @Description:
 */
public class ReactorTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Reactor reactor = new Reactor(8080);
        reactor.start();
//        Thread.sleep(1000L);
//        reactor.stop();
        Thread.sleep(500000L);
    }
}
