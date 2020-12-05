package com.linus.lab.rabbitmq.original.helloworld;

import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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

        //如果队列不存在，则创建
//        channel.queueDeclare(Consts.QUEUE_M1, false, false, false, null);

        channel.basicPublish("", "ttl_dlx", null, "Hello World!".getBytes(StandardCharsets.UTF_8));
//        channel.basicPublish("", Consts.QUEUE_M1, null, "Hello World!".getBytes(StandardCharsets.UTF_8));
        System.out.println("msg send successs!");


        channel.close();
        connection.close();
    }
}
