package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/12 18:15
 * @Description:
 */
public class Reactor {

    private ServerSocketChannel serverChannel;

    private Selector selector;

    private int port = 8888;

    private Dispatch dispatch;

    private Poller poller;

    private ThreadPoolExecutor executor;

    private volatile boolean running = false;

    public Selector getSelector() {
        return selector;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public Poller getPoller() {
        return poller;
    }

    public boolean isRunning() {
        return running;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public Reactor(int port) {
        this.port = port;
        executor = new ThreadPoolExecutor(4, 8, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(256), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void start() throws IOException {
        try {
            bindSocket();
        } catch (Throwable t) {
            this.running = false;
            doCloseServerSocket();
            throw t;
        }

        this.running = true;

        initDispatch();

        startPollerThread();

        startAcceptorThread();

    }

    private void initDispatch() {
        this.dispatch = new MultiThreadDispatch(getExecutor());
    }

    private void bindSocket() throws IOException {
        serverChannel = ServerSocketChannel.open();
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        selector = Selector.open();
    }

    protected void doCloseServerSocket() throws IOException {
        if (serverChannel != null) {
            // Close server socket
            serverChannel.close();
        }
        serverChannel = null;
    }

    private void startPollerThread() {
        poller = new Poller(this);
        Thread pollerThread = new Thread(poller,"ClientPoller");
        pollerThread.setDaemon(true);
        pollerThread.start();
        System.out.println("启动poller...");
    }

    protected void startAcceptorThread() {
        Acceptor acceptor = new Acceptor(this);
        Thread t = new Thread(acceptor, "Acceptor");
        t.setDaemon(true);
        t.start();
        System.out.println("启动acceptor...");
    }

    public void stop() throws IOException, InterruptedException {
        System.out.println("stop...");

        doCloseServerSocket();

        this.running = false;
        if (executor != null) {
            executor.shutdown();
            Thread.sleep(3000L);
            if (!executor.isShutdown()) {
                executor.shutdownNow();
            }
        }
    }

    public SocketChannel accept() throws IOException {
        return serverChannel.accept();
    }


    public boolean setSocketOptions(SocketChannel socket) {
        try {
            // 设置通道为非阻塞
            socket.configureBlocking(false);
            poller.register(socket);
            return true;
        } catch (Throwable t) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void unreg(SelectionKey sk, int readyOps) {
        reg(sk, sk.interestOps() & (~readyOps));
    }

    public void reg(SelectionKey sk, int interestOps) {
        sk.interestOps(interestOps);
    }
}
