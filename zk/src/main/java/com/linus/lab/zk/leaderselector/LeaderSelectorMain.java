package com.linus.lab.zk.leaderselector;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/20
 */
public class LeaderSelectorMain {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch =new CountDownLatch(1);

        String appName = System.getProperty("appName");

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.102:2181", retryPolicy);
        client.start();

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            public void takeLeadership(CuratorFramework client) throws Exception
            {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                System.out.println("i am leader:" + appName);

                Thread.sleep(60000);
            }
        };

        LeaderSelector selector = new LeaderSelector(client, "/leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();

        countDownLatch.await();
    }
}
