package com.test.it.proxy.jdk;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @Author: theonecai
 * @Date: Create in 2019/11/12 09:10
 * @Description:
 */
public class JdkProxyTest {

    /**
     * 参数单接口生成代理
     * 调试ProxyClassFactory.apply()方法创建代理类: byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);
     * 输出.class文件: watches: FileOutputStream f = new FileOutputStream("test.class" );f.write(proxyClassFile);f.close();
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        ServiceHandler serviceHandler = new ServiceHandler(new ServiceImpl());
        IService service = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class[]{IService.class}, serviceHandler);
        service.doSth(1);
        service.printSth("hello!!!");

        String name = "ServiceProxy";
        byte[] byteCode = ProxyGenerator.generateProxyClass(name, new Class[]{IService.class});
        try (FileOutputStream out = new FileOutputStream( name + ".class" )) {
            out.write(byteCode);
        }
        //TODO asm方式获取service的字节码
    }

    /**
     * 测试多接口生成代理
     * @throws IOException
     */
    @Test
    public void testDuplicated() throws IOException {
        ServiceHandler serviceHandler = new ServiceHandler(new ServiceBImpl());
        // 注意传入接口的顺序
        IServiceB service = (IServiceB) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class[]{IServiceB.class, IService.class}, serviceHandler);
        service.doSth(2);
        service.show("hahahahahaha");

        String name = "ServiceBProxy";
        byte[] byteCode = ProxyGenerator.generateProxyClass(name, new Class[]{IService.class});
        try (FileOutputStream out = new FileOutputStream( name + ".class" )) {
            out.write(byteCode);
        }
    }

    /**
     * 测试多接口生成代理调用异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicatedException() {
        ServiceHandler serviceHandler = new ServiceHandler(new ServiceBImpl());
        // 注意传入接口的顺序
        IServiceB service = (IServiceB) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class[]{IService.class, IServiceB.class}, serviceHandler);
        service.doSth(2);
    }
}
