package com.test.it.jdktest.nio;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: caizhh
 * @Date: Create in 18-6-13 上午9:21
 * @Description:
 */
public class TimeServer {

    private Logger logger = LoggerFactory.getLogger(TimeServer.class);

    private int port;

    private ServerSocket server;

    private volatile boolean shutdown = false;

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    public TimeServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try {
            server = new ServerSocket(port);
            //        server.bind(new InetSocketAddress(port));
            logger.info("time server start at port {}", port);
            while (!shutdown) {
                Socket socket = server.accept();
                logger.info("accept!");
                executorService.execute(new TimeServerHandler(socket));
            }
        } finally {
            stop();
        }
    }

    public void shutdownNow() throws IOException {
        logger.info("set shutdown flag =true");
        this.shutdown = true;

        Socket socket = new Socket("localhost", port);
        socket.close();

        logger.info("server shutdown");
    }

    public void stop() throws IOException {
        if (server != null) {
            server.close();
        }
        if (executorService != null) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        TimeServer timeServer = new TimeServer(8080);
        new Thread(() -> {
            try {
                timeServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(60000L);
        timeServer.shutdownNow();
    }
}

class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String receivedMsg = reader.readLine();
                if (receivedMsg == null) {
                    break;
                }
                if ("CURRENT_TIME".equals(receivedMsg)) {
                    writer.println(new Date().toString());
                } else {
                    writer.println("wrong cmd");
                }
            }
            System.out.println("work done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
