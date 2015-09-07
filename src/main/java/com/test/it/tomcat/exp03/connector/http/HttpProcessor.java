package com.test.it.tomcat.exp03.connector.http;

import javax.servlet.ServletException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class HttpProcessor {

    private HttpRequest request;

    private HttpResponse response;

    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector httpConnector) {

    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");
            parseRequest(input, output);
            parseHeaders(input);

            if(request.getRequestURI().startsWith("/servlet")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseHeaders(SocketInputStream input) {

    }

    private void parseRequest(SocketInputStream input, OutputStream output) throws ServletException {
        input.readRequestLine(requestLine);
        String method = new String(requestLine.getMethod(), 0, requestLine.getMethodEnd());
        String uri = null;
        String protocol = new String(requestLine.getProtocol(), 0, requestLine.getProtocolEnd());
        if(method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if(requestLine.getUriEnd() < 1) {
            throw new ServletException("Missing HTTP request URI");
        }

        int question = requestLine.indexOf('?');
        if(question >= 0) {
            request.setQueryString(new String(requestLine.getUri(), question+1, requestLine.getUriEnd()-question-1));
            uri = new String(requestLine.getUri(), 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.getUri(), 0, requestLine.getUriEnd());
        }

        if(!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if(pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if(pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if(semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if(semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
        
        String normalizedUri = normalize(uri);
        request.setMethod(method);
        request.setProtocol(protocol);

        if(normalizedUri != null) {
            request.setRequestURI(normalizedUri);
        } else {
            request.setRequestURI(uri);
        }
        if(normalizedUri == null) {
            throw new ServletException("Invalid URI:'" + uri + "'");
        }

    }

    private String normalize(String uri) {
        return uri;
    }
}
