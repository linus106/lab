package com.linus.lab.zk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/18
 */
public class ZookeeperFactory {

    private static ZooKeeper zooKeeper = null;

    public static ZooKeeper getZookeeper() throws IOException {
        if (zooKeeper == null) {
            zooKeeper = new ZooKeeper("192.168.0.102:2181", 30 * 1000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.EventType.None == event.getType() && event.getState() == Event.KeeperState.SyncConnected) {
                        System.out.println("连接建立");
                    }
                }
            });
        }
        return zooKeeper;
    }
}
