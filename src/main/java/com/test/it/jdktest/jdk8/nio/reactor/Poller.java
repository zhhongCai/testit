package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/12 18:15
 * @Description:
 */
public class Poller implements Runnable {

    private Reactor endpoint;

    private Selector selector;

    private boolean close = false;

    private LinkedBlockingQueue<PollerEvent> eventCache;

    public Poller(Reactor endpoint) {
        this.endpoint = endpoint;

        this.selector = endpoint.getSelector();

        eventCache = new LinkedBlockingQueue<>(256);
    }

    @Override
    public void run() {
        while (endpoint.isRunning()) {
            events();

            int keyCount = 0;
            try {
                keyCount = selector.selectNow();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (keyCount == 0) {
                continue;
            }

            if (close) {
                events();
                break;
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            if (keys != null) {
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    processKey(it.next());
                    it.remove();
                }
            }
        }

    }

    public void register(SocketChannel socket){
        Selector selector = endpoint.getSelector();
        PollerEvent event = new PollerEvent(socket, PollerEvent.OP_REGISTER, selector);
        eventCache.offer(event);
    }

    private void events() {
        int size = eventCache.size();
        PollerEvent pe = null;
        for (int i = 0; i < size && (pe = eventCache.poll()) != null; i++) {
            pe.run();
        }
    }

    private void processKey(SelectionKey sk) {
        System.out.println("processKey sk.interestOps=" + sk.interestOps());
        try {
            if (sk.isValid()) {
                if (sk.isReadable() || sk.isWritable()) {
                    boolean closeSocket = false;
                    if (sk.isReadable()) {
                        if (!processSocket(sk)) {
                            closeSocket = true;
                        }
                    }
                    if (!closeSocket && sk.isWritable()) {
                        if (!processSocket(sk)) {
                            closeSocket = true;
                        }
                    }
                    if (closeSocket) {
                        cancelledKey(sk);
                    }
                }
            } else {
                // Invalid key
                cancelledKey(sk);
            }
        } catch (CancelledKeyException ckx) {
            cancelledKey(sk);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private boolean processSocket(SelectionKey sk) {
        try {
            if (close) {
                cancelledKey(sk);
            } else if (sk.isValid()) {
                if (sk.isReadable() || sk.isWritable()) {

                    endpoint.unreg(sk, sk.readyOps());

                    boolean closeSocket = false;
                    // Read goes before write
                    if (sk.isReadable()) {
                        if (!endpoint.getDispatch().processSocket(sk)) {
                            closeSocket = true;
                        }
                    }
                    if (!closeSocket && sk.isWritable()) {
                        if (!endpoint.getDispatch().processSocket(sk)) {
                            closeSocket = true;
                        }
                    }
                    if (closeSocket) {
                        cancelledKey(sk);
                    }
                }
            } else {
                cancelledKey(sk);
            }
        } catch (CancelledKeyException ckx) {
            cancelledKey(sk);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return true;
    }

    private void add(SelectionKey sk, int interestOps) {
        PollerEvent event = new PollerEvent((SocketChannel) sk.attachment(), interestOps, sk.selector());
        eventCache.offer(event);
    }

    private void cancelledKey(SelectionKey sk) {
        System.out.println("cancelledKey...");
        try {
            if (sk != null) {
                sk.attach(null);
                if (sk.isValid()) {
                    sk.cancel();
                }
                if (sk.channel().isOpen()) {
                    sk.channel().close();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}