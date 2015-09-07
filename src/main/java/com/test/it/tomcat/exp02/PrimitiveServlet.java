package com.test.it.tomcat.exp02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by caizh on 2015/8/31 0031.
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("PrimitiveServlet init...");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("PrimitiveServlet service...");
        PrintWriter out = servletResponse.getWriter();
        out.print("Hello servlet\n");
        out.print("Goodbye");
        out.flush();
        out.close();
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("PrimitiveServlet destroy...");
    }
}
