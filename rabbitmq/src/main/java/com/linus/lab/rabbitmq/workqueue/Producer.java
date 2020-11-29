package com.linus.lab.rabbitmq.workqueue;

import com.linus.lab.rabbitmq.util.Consts;
import com.linus.lab.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.AMQP;
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

        final AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(Consts.QUEUE_M2, false, false, false, null);

        for (int i = 0; i< 100;i++) {
            String msg = "task" + i;
            channel.basicPublish("", Consts.QUEUE_M2, null, msg.getBytes(StandardCharsets.UTF_8));
        }




        channel.close();
        connection.close();
    }
}
