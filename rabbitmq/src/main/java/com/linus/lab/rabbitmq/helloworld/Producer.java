package com.linus.lab.rabbitmq.helloworld;

import com.linus.lab.rabbitmq.util.Consts;
import com.linus.lab.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

//        channel.queueDeclare(Consts.QUEUE_M1, false, false, false, null);

        channel.basicPublish("", "123123123", null, "Hello World!".getBytes(StandardCharsets.UTF_8));
//        channel.basicPublish("", Consts.QUEUE_M1, null, "Hello World!".getBytes(StandardCharsets.UTF_8));
        System.out.println("msg send successs!");


        channel.close();
        connection.close();
    }
}
