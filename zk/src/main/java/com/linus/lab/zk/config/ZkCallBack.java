package com.linus.lab.zk.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/18
 */
public class ZkCallBack {

    public static void main(String[] args) throws IOException, InterruptedException {
        final ZooKeeper zookeeper = ZookeeperFactory.getZookeeper();



        zookeeper.getData("/test", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("callback thread: " + Thread.currentThread().getName());
            }
        }, "test");


        Thread.sleep(10 * 1000);
    }
}
