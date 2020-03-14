package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/13 10:56
 * @Description:
 */
public class PollerEvent implements Runnable {

    public static final int OP_REGISTER = 0x100; //register interest op

    private SocketChannel socket;

    private int interestOps;

    private Selector selector;

    public PollerEvent(SocketChannel socket, int interestOps, Selector selector) {
        this.socket = socket;
        this.interestOps = interestOps;
        this.selector = selector;
    }

    @Override
    public void run() {
        System.out.println("pollerEvent执行中...");
        if (interestOps == OP_REGISTER) {
            try {
                socket.register(selector, SelectionKey.OP_READ, socket);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        } else {
            final SelectionKey key = socket.keyFor(selector);
            try {
                if (key == null) {
                    try {
                        socket.close();
                    } catch (Exception ignore) {
                    }
                } else {
                    final SocketChannel socketChannel = (SocketChannel) key.attachment();
                    if (socketChannel != null) {
                        int ops = key.interestOps() | interestOps;
                        key.interestOps(ops);
                        System.out.println("sk.interestOps=" + key.interestOps());
                    } else {
                        cancelKey(key);
                    }
                }
            } catch (CancelledKeyException ckx) {
                try {
                    cancelKey(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("pollerEvent执行完成...");

    }

    private void cancelKey(SelectionKey key) {
        key.attach(null);
        if (key.isValid()) {
            key.cancel();
        }
        if (key.channel().isOpen()) {
            try {
                key.channel().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
