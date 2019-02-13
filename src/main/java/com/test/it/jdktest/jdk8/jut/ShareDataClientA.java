package com.test.it.jdktest.jdk8.jut;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by caizh on 2015/8/14.
 */
public class ShareDataClientA implements Observer {
    private String argFromShareData;

    public void doSth(ShareData shareData) {
        System.out.println("ClientA before doSth: " + shareData);
        shareData.setName(shareData.getName() + ", ClientA");
        System.out.println("ClientA after doSth: " + shareData);
    }

    @Override
    public void update(Observable data, Object arg) {
        if(null != arg) {
            argFromShareData = arg.toString();
        }
        System.out.println("clientA.update: data=" + data + ", arg=" + arg);
    }

    public String getArgFromShareData() {
        return argFromShareData;
    }

    public void setArgFromShareData(String argFromShareData) {
        this.argFromShareData = argFromShareData;
    }
}
