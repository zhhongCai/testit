package com.test.it.jdktest.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: caizhh
 * @Date: Create in 18-6-13 上午10:08
 * @Description:
 */
public class TimeClient {
    public static void main(String[] args) {

        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    Socket socket = null;
                    BufferedReader reader = null;
                    PrintWriter writer = null;
                    try {
                        socket = new Socket("localhost", 8080);
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        writer = new PrintWriter(socket.getOutputStream(), true);

                        writer.println("CURRENT_TIME");
                        String retMsg = reader.readLine();
                        System.out.println(retMsg);

                        writer.println("ERROR CMD");
                        retMsg = reader.readLine();
                        System.out.println(retMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (writer != null) {
                            writer.close();
                        }
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
