package com.linus.lab.rabbitmq.original.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/29
 */
public class RabbitUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.101");
        factory.setPort(5672);
        factory.setVirtualHost("lab");
        factory.setUsername("linus");
        factory.setPassword("linus");

        Connection connection = factory.newConnection();
        return connection;
    }
}
