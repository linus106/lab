package com.linus.lab.zk.cluster;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/6
 */
public class ZkClusterMain {

    public static void main(String[] args) throws IOException {
        final ZooKeeper zooKeeper = new ZooKeeper("192.168.0.101:2181,192.168.0.101:2182,192.168.0.101:2183", 30 * 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected
                        && event.getType()== Event.EventType.None){
                    System.out.println("连接建立");
                }
            }
        }, true);


        Stat stat = new Stat();

        while (true) {//写请求不会改变连接的节点，如果连接的是从节点，从节点会负责转发请求。
            try {
                byte[] data = zooKeeper.getData("/lab", false, stat);
                String res = new String(data);
                System.out.println(res);
                res = String.valueOf(Integer.valueOf(res) + 1);
                final Stat stat1 = zooKeeper.setData("/lab", res.getBytes(), stat.getVersion());
                System.out.println(new String(data));
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                //TODO 连接节点挂掉，要做业务补偿
                e.printStackTrace();
            }
        }
    }
}
