package com.test.it.tomcat.exp03.connector.http;

/**
 * Created by caizh on 2015/9/1 0001.
 */
public class HttpRequestLine {
    private char[] uri;
    private char[] method;
    private int methodEnd;
    private char[] protocol;
    private int protocolEnd;
    private int uriEnd;

    public int getUriEnd() {
        return uriEnd;
    }

    public void setUriEnd(int uriEnd) {
        this.uriEnd = uriEnd;
    }

    public char[] getMethod() {
        return method;
    }

    public void setMethod(char[] method) {
        this.method = method;
    }

    public int getMethodEnd() {
        return methodEnd;
    }

    public void setMethodEnd(int methodEnd) {
        this.methodEnd = methodEnd;
    }

    public char[] getProtocol() {
        return protocol;
    }

    public void setProtocol(char[] protocol) {
        this.protocol = protocol;
    }

    public int getProtocolEnd() {
        return protocolEnd;
    }

    public void setProtocolEnd(int protocolEnd) {
        this.protocolEnd = protocolEnd;
    }

    public int indexOf(char c) {
        if(uri == null) {
            return -1;
        }
        for(int i = 0; i < uri.length; i++) {
            if(uri[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public char[] getUri() {
        return uri;
    }

    public void setUri(char[] uri) {
        this.uri = uri;
    }
}
