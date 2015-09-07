package com.test.it.tomcat.exp02;

import java.io.IOException;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
