package com.linus.lab.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/14
 */
@SpringBootApplication
public class SpringMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }


    @Bean(initMethod = "start")
    public CuratorFramework curator() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient("192.168.0.102:2181", retryPolicy);
    }
}
