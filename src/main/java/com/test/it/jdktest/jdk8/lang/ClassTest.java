package com.test.it.jdktest.jdk8.lang;

import java.net.URL;

/**
 * Author: caizh
 * CreateTime: 2015/5/28 14:16
 * Version: 1.0
 */
public class ClassTest {
    private static String va = "a";

    private String b = "b";

    static {
        System.out.println(va);
        System.out.println("static 1");
    }

    public ClassTest() {
        System.out.println(va);
        System.out.println(b);
        System.out.println("constructor..");
    }

    static {
        System.out.println(va);
        System.out.println("static 2");
    }

    public static void main(String[] args) {
        //Java默认提供的三个ClassLoader

        //vm 参数-verbose: 打印加载类信息

        // 1. BootStrap ClassLoader：称为启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));


        // 2. Extension ClassLoader：称为扩展类加载器，负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar

        // 3. App ClassLoader：称为系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件

        // 双亲委托模型: 避免重复加载, 考虑到安全因素

        System.out.println("==============");

        new ClassTest();

    }
}
