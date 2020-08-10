package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @Author: theonecai
 * @Date: Create in 2020/3/12 18:15
 * @Description:
 */
public class Acceptor implements Runnable {

    private Reactor endpoint;

    public Acceptor(Reactor endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        while (endpoint.isRunning()) {
            SocketChannel socketChannel = null;
            try {
                socketChannel = endpoint.accept();
                if (socketChannel == null) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    if (socketChannel != null) {
                        socketChannel.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            endpoint.setSocketOptions(socketChannel);
        }
    }
}
