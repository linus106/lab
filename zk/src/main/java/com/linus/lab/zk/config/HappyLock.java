package com.linus.lab.zk.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/18
 */
public class HappyLock {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {


        ZooKeeper zooKeeper = ZookeeperFactory.getZookeeper();


        Stat stat = new Stat();
        zooKeeper.getData("/happy_lock", false, stat);

        int version = stat.getAversion();

        zooKeeper.setData("/happy_lock", "456".getBytes(), version);

    }
}
