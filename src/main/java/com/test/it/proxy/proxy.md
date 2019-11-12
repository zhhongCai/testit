#　代理


## asm

## aspectj

## cglib

[cglib源码github地址](https://github.com/cglib/cglib  "Markdown")

[博文推荐:Create Proxies Dynamically Using CGLIB Library](http://jnb.ociweb.com/jnb/jnbNov2005.html "Markdown")


## jdk动态代理

### jdk动态代理的使用

1. 接口类：

```java
public interface Inteface {
}

public interface IService extends Inteface {

    public String doSth(Integer count);

    public void printSth(String str);
}

public interface IServiceB extends Inteface {
    public String doSth(Integer count);

    public void show(String str);
}

```

2. 实现类:

```java
public class ServiceImpl implements IService {
    @Override
    public String doSth(Integer count) {
        return "ServiceImpl doSth " + count;
    }

    @Override
    public void printSth(String str) {
        System.out.println("ServiceImpl printSth:" + str);
    }
}


public class ServiceBImpl implements IServiceB {
    @Override
    public String doSth(Integer count) {
        return "ServiceBImpl doSth " + count;
    }

    @Override
    public void show(String str) {
        System.out.println("ServiceBImpl show: " + str);
    }
}

```

3. InvocationHandler实现类:
```java
public class ServiceHandler implements InvocationHandler {

    private Inteface target;

    public ServiceHandler(Inteface iService) {
        this.target = iService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before " + method.getName() + " call");
        Object obj = method.invoke(target, args);
        System.out.println("result=" + obj);
        System.out.println("after " + method.getName() + " call");
        return obj;
    }
}

```

4. 测试使用类:
```java
public class JdkProxyTest {

    /**
     * 参数单接口生成代理
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

```

生成的动态代理类:

```java

import com.test.it.proxy.jdk.IService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class ServiceProxy extends Proxy implements IService {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public ServiceProxy(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String doSth(Integer var1) throws  {
        try {
            return (String)super.h.invoke(this, m3, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.test.it.proxy.jdk.IService").getMethod("doSth", Class.forName("java.lang.Integer"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

```

```java
import com.test.it.proxy.jdk.IService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class ServiceBProxy extends Proxy implements IService {
    private static Method m1;
    private static Method m4;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public ServiceBProxy(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void printSth(String var1) throws  {
        try {
            super.h.invoke(this, m4, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String doSth(Integer var1) throws  {
        try {
            return (String)super.h.invoke(this, m3, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m4 = Class.forName("com.test.it.proxy.jdk.IService").getMethod("printSth", Class.forName("java.lang.String"));
            m3 = Class.forName("com.test.it.proxy.jdk.IService").getMethod("doSth", Class.forName("java.lang.Integer"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
```


### 总结

一个代理类有下列特点([见Proxy类源码注释](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html))：

1. 如果被代理的接口都是public的,代理类是public final 非abstract(见上文ServiceProxy)
2. 如果任一被代理接口是非public的,代理类是非public final 非abstract
3. 代理类的全类名是不明确的.以"$Proxy"开头的类名空间需要给代理类预留
4. 代理类继承了java.lang.reflect.Proxy
5. 代理类按顺序实现了创建时指定的接口
6. 代理类如果实现了一个非public接口,那它的包名跟该接口一致,否则包名是不明确的.注意包的封装性不会
   阻止一个代理类在运行时被定义在特定包中,同一类加载器中相同包名的类也不会.
7. 由于代理类实现了创建时指定的所有接口,代理类实例上调用getInterfaces将返回与创建时指定接口一样的数组（相同顺序）；
   调用getMethods将返回创建时指定接口的所有方法的数组；调用getMethod将在代理接口中查找对应的方法
8. Proxy.isProxyClass如果传入的是一个代理类(Proxy.getProxyClass返回的或Proxy.newProxyInstance返回的)将返回true,否则返回false.
9. 代理类的java.security.ProtectionDomain和bootstrap类加载器加载的系统类是一样的，比如java.lang.Object，因为代理类的代码是由受信任
   的系统代码生成的.这个保护域也将被授权.
10. 每个代理类都有一个含一个参数（该参数实现了InvocationHandler接口）的构造函数,为代理类实例设置invoocation handler.除了使用反射api
   来访问public的构造函数,代理类实例可以通过Proxy.newProxyInstance()方法来创建.

一个代理类实例有下列特点:

1. 代理类实例 instanceof 创建代理类时指定的接口 将返回ture, 可以强制类型转换： (创建代理类时指定的接口)代理类实，比如: 
   IService service = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, serviceHandler);
   service instanceof IService 返回 true.
2. 每个代理类实例都有一个关联的invocation handler(通过构造函数参数传入),Proxy.getInvocationHandler方法将返回这个invocation handler
3. 调用代理类实例的接口方法将被转发到invocation handler的invoke方法.
4. 调用代理类实例的hashCode,equals,toString方法也转发到invocation handler的invoke方法（见ServiceProxy）,其中传入invoke的method参数的声明类是Object.
   其他继承自Object的方法不会被代理类覆盖.见上文中m0,m1,m2取的是Object中的hashCode,equals,toString.
   
多个接口时重复方法:

多个接口中含有相同方法,此时,创建代理类传入的接口顺序将有意义.调用这个方法时传入invcation handler的method参数的声明类是接口列表中第一个含有该方法的接口.
见JdkProxyTest.testDuplicatedException()

jdk动态代理只能针对接口,普通类无法使用.原因是生成的代理类继承了Proxy类且实现对应接口.

注意InvocationHandler.invoke方法的Object proxy, Method method这两个参数,从生成的代理类ServiceProxy可以看出proxy传的的this（即代理类）
调doSth方法时,method传的是 m3 = Class.forName("com.test.it.proxy.jdk.IService").getMethod("doSth", Class.forName("java.lang.Integer")).


[Java 8 Multiple Inheritance Conflict Resolution Rules and Diamond Problem](https://www.javabrahman.com/java-8/java-8-multiple-inheritance-conflict-resolution-rules-and-diamond-problem/)

[源码](https://github.com/zhhongCai/testit/tree/master/src/main/java/com/test/it/proxy/jdk)