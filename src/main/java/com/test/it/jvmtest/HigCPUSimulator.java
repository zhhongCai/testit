package com.test.it.jvmtest;

/**
 * Created by caizh on 16-6-26.
 */
public class HigCPUSimulator {
    private final static long NB_ITERATIONS = 50000000000L;

    private final static String DATA_PREFIX = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada" +
            "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada" +
            "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada" +
            "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada" +
            "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata";

    public static void main(String[] args) {
        System.out.println("High CPU Simulator 1.0");

        try {
            for (int i = 0; i < NB_ITERATIONS; i++) {
                String data = DATA_PREFIX + i;
            }
        } catch (Throwable any) {
            System.out.println("Unexpected Exception!" + any.getMessage() + " ["+any+"] ");
        }
        System.out.println("High CPU Simulator Done!");
    }
}
