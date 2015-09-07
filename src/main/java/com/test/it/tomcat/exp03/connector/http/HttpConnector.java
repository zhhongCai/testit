package com.test.it.tomcat.exp03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class HttpConnector implements Runnable {
    boolean stopped;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8088;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!stopped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            HttpProcessor process = new HttpProcessor(this);
            process.process(socket);
        }
    }
}
