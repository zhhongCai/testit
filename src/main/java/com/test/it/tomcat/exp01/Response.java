package com.test.it.tomcat.exp01;

import java.io.*;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;

    private Request request;

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fin = null;
        try {
            if(request.getUri() == null) {
                System.err.println("uri is null");
                return;
            }
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if(file.exists()) {
                fin = new FileInputStream(file);
                int ch;
                while((ch = fin.read(bytes, 0, BUFFER_SIZE)) != -1) {
                    output.write(bytes, 0, ch);
                }
            } else {
                System.out.println(file.getAbsolutePath());
                StringBuilder sb = new StringBuilder("HTTP/1.1 404 File not found\r\n");
                sb.append("Content-Type: text/html\r\n");
                sb.append("content-Length: 23\r\n");
                sb.append("\r\n");
                sb.append("<h1>File Not Found</h1>");
                output.write(sb.toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fin != null) {
                fin.close();
            }
        }
    }
}
