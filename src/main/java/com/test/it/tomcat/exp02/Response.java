package com.test.it.tomcat.exp02;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;

    private Request request;

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    void setRequest(Request request) {
        this.request = request;
    }

    void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fin = null;
        try {
            if(request.getUri() == null) {
                System.err.println("uri is null");
                return;
            }
            File file = new File(Constants.WEB_ROOT, request.getUri());
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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output, true);
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
