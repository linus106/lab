package com.linus.lab.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/18
 */
public class CuratorStart {

    public static void main(String[] args) throws Exception {


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.102:2181", retryPolicy);
        client.start();



//        client.create().creatingParentsIfNeeded().forPath("/node-parent/sub-node-1");


        // _c_874118bd-b1fe-4910-b275-a2b96c2c3cd8-node-2 实际节点，保护模式
        client.create().withProtection().forPath("/node-2", "123".getBytes());
    }
}
