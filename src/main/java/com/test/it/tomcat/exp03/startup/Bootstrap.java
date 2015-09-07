package com.test.it.tomcat.exp03.startup;

import com.test.it.tomcat.exp03.connector.http.HttpConnector;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public final class Bootstrap {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
