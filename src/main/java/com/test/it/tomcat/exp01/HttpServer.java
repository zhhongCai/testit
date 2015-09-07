package com.test.it.tomcat.exp01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class HttpServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static final String SHUTDOWN_CMD = "/SHUTDOWN";

    private boolean shutdown = false;

    public void run(int port) {
        ServerSocket socketServer = null;
        System.out.println("http server running...");
        try {
            socketServer = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = socketServer.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                output.flush();
                socket.close();

                shutdown = SHUTDOWN_CMD.equals(request.getUri());
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        System.out.println("http server shutdown.");
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.run(8088);
    }
}
