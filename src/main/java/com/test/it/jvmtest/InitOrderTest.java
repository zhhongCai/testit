package com.test.it.jvmtest;

/**
 * @Author: caizhh
 * @Date: Create in 18-12-28 上午10:51
 * @Description:
 */
public class InitOrderTest {
    protected int a = 11;
    protected Integer b = 22;
    protected static Integer c = 33;

    static {
        System.out.println("静态代码块1==============c=" + c);
        c = 2;
    }

    {
        System.out.println("代码块1==============a=" + a);
        System.out.println("代码块1==============b=" + b);
        System.out.println("代码块1==============c=" + c);
    }

    public InitOrderTest() {
        System.out.println("构造函数==============a=" + a);
        System.out.println("构造函数==============b=" + b);
        System.out.println("构造函数==============c=" + c);
        c = 4;
    }

    {
        System.out.println("代码块2==============a=" + a);
        System.out.println("代码块2==============b=" + b);
        System.out.println("代码块2==============c=" + c);
    }

    static {
        System.out.println("静态代码块2==============c=" + c);
        c = 3;
    }

    public static void main(String[] args) {
        SubInitOrderTest test2 = new SubInitOrderTest();
        System.out.println("------------------------------------");
        InitOrderTest test = new InitOrderTest();
        System.out.println("------------------------------------");
        test = new InitOrderTest();
        System.out.println("------------------------------------");
        test2 = new SubInitOrderTest();
    }

    static class SubInitOrderTest extends InitOrderTest {

        private int d = 1;
        private static int e = 1;

        {
            System.out.println("子类代码块1==============a=" + a);
            System.out.println("子类代码块1==============b=" + b);
            System.out.println("子类代码块1==============c=" + c);
            System.out.println("子类代码块1==============e=" + e);
        }

        static {
            System.out.println("子类静态代码块1-------e=" + e);
            e = 2;
        }

        public SubInitOrderTest() {
            System.out.println("子类构造函数==============a=" + a);
            System.out.println("子类构造函数==============b=" + b);
            System.out.println("子类构造函数==============c=" + c);
            System.out.println("子类构造函数==============e=" + e);
            c = 5;
        }

        {
            System.out.println("子类代码块2==============a=" + a);
            System.out.println("子类代码块2==============b=" + b);
            System.out.println("子类代码块2==============c=" + c);
            System.out.println("子类代码块2==============e=" + e);
        }

        static {
            System.out.println("子类静态代码块2-------e=" + e);
            e = 3;
        }
    }
}
