package com.test.it.tomcat.exp01;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for(int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
        System.out.println("uri=" + uri);
    }

    private String parseUri(String requestString) {
        int index, index2;
        index = requestString.indexOf(' ');
        if(index != -1) {
            index2 = requestString.indexOf(' ', index + 1);
            if(index2 > index) {
                return requestString.substring(index + 1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
