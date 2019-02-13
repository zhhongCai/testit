package com.test.it.jdktest.jdk8.jut;

/**
 * Created by caizh on 2015/8/14.
 */
public class ObserverTest {
    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData("shareData01", "shareData01's description");
        ShareDataClientA clientA = new ShareDataClientA();
        ShareDataClientB clientB = new ShareDataClientB();
        shareData.addObserver(clientA);
        shareData.addObserver(clientB);

        clientA.doSth(shareData);
        System.out.println(clientA.getArgFromShareData());
        System.out.println(clientB.getArgFromShareData());

        System.out.println("----------------");

        clientB.doSth(shareData);
        System.out.println(clientA.getArgFromShareData());
        System.out.println(clientB.getArgFromShareData());

    }
}
