package com.linus.lab.zk.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/18
 */
public class SimpleConfigCenter {

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        final ZooKeeper zooKeeper = new ZooKeeper("192.168.0.102:2181", 30 * 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.EventType.None == event.getType() && event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("连接建立");
                }
            }
        });

        ConfigBean configBean = new ConfigBean();
        configBean.setKey("123");
        configBean.setName("456");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(configBean);

        zooKeeper.create("/config", bytes,  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.getData("/config", new Watcher() {
            @Override
            @SneakyThrows
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged && event.getPath() != null && event.getPath().equals("/config")) {
                    System.out.println("数据发生了变化,新数据为:");
                    byte[] data = zooKeeper.getData("/config", this, null);
                    ConfigBean config = objectMapper.readValue(data, ConfigBean.class);
                    System.out.println(config);
                }
            }
        }, null);

        countDownLatch.await();

    }
}
