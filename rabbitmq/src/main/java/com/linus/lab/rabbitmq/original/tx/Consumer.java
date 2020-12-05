package com.linus.lab.rabbitmq.original.tx;

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


        String queue = "tx_queue_a";

        // 准备工作
        channel.queueDeclare(queue, false, false, false, null);//声明队列

        channel.basicConsume(queue, false, new Receiver(channel));
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

            channel.basicAck(envelope.getDeliveryTag(), false);//ack消息
            channel.basicNack(envelope.getDeliveryTag(), false, false);//uack requeue决定是否放回原有队列
        }
    }
}
