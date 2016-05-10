package com.test.it.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Created by caizh on 2016/4/18 0018.
 */
public class DataMonitor implements Watcher, AsyncCallback.StatCallback {

    ZooKeeper zk;
    String znode;
    Watcher chaineWatcher;
    boolean dead;
    DataMonitorListener listener;
    byte prevData[];

    public DataMonitor(ZooKeeper zk, String znode, Watcher chaineWatcher, DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chaineWatcher = chaineWatcher;
        this.listener = listener;
        zk.exists(znode, true, this, null);
    }

    public void processResult(int i, String s, Object o, Stat stat) {

    }

    public void process(WatchedEvent watchedEvent) {

    }


    public interface DataMonitorListener {
        void exists(byte data[]);
        void closing(int rc);
    }
}
