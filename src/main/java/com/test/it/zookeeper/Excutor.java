package com.test.it.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by caizh on 2016/4/18 0018.
 */
public class Excutor implements Watcher, Runnable, DataMonitor.DataMonitorListener {
    public void run() {

    }

    public void process(WatchedEvent watchedEvent) {

    }

    public void exists(byte[] data) {

    }

    public void closing(int rc) {

    }
}
