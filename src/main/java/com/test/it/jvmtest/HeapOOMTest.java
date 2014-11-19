package com.test.it.jvmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * VM Args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * Author: caizh
 * CreateTime: 2014/10/11 15:43
 * Version: 1.0
 */
public class HeapOOMTest {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true) {
            list.add(new OOMObject());
        }

    }
}
