package com.test.it.jdktest.jdk8.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SizeOfTest {
    public static void main(String[] paramArrayOfString)
            throws FileNotFoundException
    {
        print("Starting test...");
        SizeOf.skipStaticField(true);
        SizeOf.skipFinalField(true);
        SizeOf.skipFlyweightObject(true);
        SizeOf.setLogOutputStream(new FileOutputStream("log.txt"));
        Integer localInteger = Integer.valueOf(1);
        int i = 0;
        long l = 0L;
        Dummy localDummy = new Dummy();
        ArrayList localArrayList = new ArrayList();
        Object[] arrayOfObject = new Object['?'];
        localDummy.dummy = localDummy;
        localDummy.dummy2 = localDummy;
        print("simple obj: \t", SizeOf.deepSizeOf(localDummy));
        print("int: \t\t", SizeOf.sizeOf(Integer.valueOf(i)));
        print("long: \t\t", SizeOf.sizeOf(Long.valueOf(l)));
        print("char: \t\t", SizeOf.sizeOf(Character.valueOf('a')));
        print("double: \t\t", SizeOf.deepSizeOf(Double.valueOf(0.3D)));
        print("boolean: \t\t", SizeOf.sizeOf(Boolean.valueOf(true)));
        print("Integer: \t\t", SizeOf.sizeOf(Integer.valueOf(2)));
        print("empty string: \t", SizeOf.sizeOf(""));
        print("not empty string: \t", SizeOf.sizeOf("aaaa"));
        print("not empty string: \t", SizeOf.sizeOf("aaaaaaaa"));
        print("simple obj: \t", SizeOf.sizeOf(localDummy));
        print("simple obj: \t", SizeOf.deepSizeOf(localDummy));
        print("empty list: \t", SizeOf.sizeOf(localArrayList));
        for (int j = 1; j < 10; j++) {
            localArrayList.add(Integer.valueOf(j));
        }
        print("10 list: \t", SizeOf.sizeOf(localArrayList));
        for (int j = 11; j < 20; j++) {
            localArrayList.add(Integer.valueOf(j));
        }
        print("20 list no static: \t", SizeOf.sizeOf(localArrayList));
        print("1000 o arr: \t", SizeOf.deepSizeOf(arrayOfObject));
    }

    public static void print(String paramString, long paramLong)
    {
        print(paramString + SizeOf.humanReadable(paramLong));
    }

    public static void print(String paramString)
    {
        System.out.println(paramString);
    }

    public static void heinz()
            throws IllegalArgumentException, IllegalAccessException, IOException
    {
        measureSize(new Object());
        measureSize(new HashMap());
        measureSize(new LinkedHashMap());
        measureSize(new ReentrantReadWriteLock());
        measureSize(new byte['?']);
        measureSize(new boolean['?']);
        measureSize(new String("Hello World".toCharArray()));
        measureSize("Hello World");
        measureSize(Integer.valueOf(10));
        measureSize(Integer.valueOf(100));
        measureSize(Integer.valueOf(1000));
        measureSize(new Parent());
        measureSize(new Kid());
        measureSize(Thread.State.TERMINATED);
        measureSize(Boolean.TRUE);
    }

    private static void measureSize(Object paramObject)
    {
        print(paramObject.getClass().getSimpleName() + ", shallow=" + SizeOf.sizeOf(paramObject) + ", deep=" + SizeOf.deepSizeOf(paramObject));
    }

    private static class Kid
            extends SizeOfTest.Parent
    {
        private boolean b;
        private float f;

        private Kid()
        {
            super();
        }
    }

    private static class Parent
    {
        private int i;
        private boolean b;
        private long l;
    }
}
