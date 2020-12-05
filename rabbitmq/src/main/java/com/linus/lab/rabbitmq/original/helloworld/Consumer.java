package com.linus.lab.rabbitmq.original.helloworld;

import com.linus.lab.rabbitmq.original.util.Consts;
import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Consts.QUEUE_M1, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicConsume(Consts.QUEUE_M1, false, new Receiver(channel));
    }

    static class Receiver extends DefaultConsumer {

        private Channel channel;

        public Receiver(Channel channel) {
            super(channel);
            this.channel = channel;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body, "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
