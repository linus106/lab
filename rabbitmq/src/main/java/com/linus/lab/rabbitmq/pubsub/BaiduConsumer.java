package com.linus.lab.rabbitmq.pubsub;

import com.linus.lab.rabbitmq.util.Consts;
import com.linus.lab.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/29
 */
public class BaiduConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Consts.QUEUE_WEATHER_BAIDU, false, false, false,null);
        channel.queueBind(Consts.QUEUE_WEATHER_BAIDU, Consts.EXCHANGE_WEATHER, "");

        channel.basicConsume(Consts.QUEUE_WEATHER_BAIDU, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Baidu Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
