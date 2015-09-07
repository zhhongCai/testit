package com.test.it.tomcat.exp14.startup;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.*;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;

/**
 * Created by caizh on 2015/9/2 0002.
 */
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        System.setProperty("catalina.base", System.getProperty("user.dir"));
        Connector connector = new Connector();
        connector.setPort(8088);

        Wrapper wrapper = new StandardWrapper();
        wrapper.setName("Primitive");
        wrapper.setServletClass("com.test.it.tomcat.exp02.PrimitiveServlet");
        Context context = new StandardContext();
        context.setPath("/");
        context.setDocBase("app1");
        context.addChild(wrapper);
        LifecycleListener lifecycleListener = new ContextConfig();
        ((Lifecycle)context).addLifecycleListener(lifecycleListener);

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        context.addServletMapping("Primitive", "Primitive");

        Engine engine = new StandardEngine();
        engine.addChild(host);
        engine.setDefaultHost("localhost");

        Service service = new StandardService();
        service.setName("stand-alone service");

        Server server = new StandardServer();
        server.addService(service);
        service.addConnector(connector);

        service.setContainer(engine);
        if(server instanceof Lifecycle) {
            server.initialize();
            ((Lifecycle) server).start();
            server.await();
        }

        if(server instanceof Lifecycle) {
            ((Lifecycle) server).stop();
        }
    }
}
