package com.test.it.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by caizh on 2015/8/26 0026.
 */
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

//        ServletRegistration.Dynamic dispatcher =
//                servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
    }
}
