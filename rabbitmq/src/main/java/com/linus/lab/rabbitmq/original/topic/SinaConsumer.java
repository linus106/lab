package com.linus.lab.rabbitmq.original.topic;

import com.linus.lab.rabbitmq.original.util.Consts;
import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/29
 */
public class SinaConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Consts.QUEUE_WEATHER_SINA, false, false, false,null);
        channel.queueBind(Consts.QUEUE_WEATHER_SINA, Consts.EXCHANGE_WEATHER_TOPIC, "china.*");
//        channel.queueBind(Consts.QUEUE_WEATHER_SINA, Consts.EXCHANGE_WEATHER_TOPIC, "china.beijing");

        channel.basicConsume(Consts.QUEUE_WEATHER_SINA, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Sina Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
