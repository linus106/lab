package com.linus.lab.rabbitmq.original.tx;

import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {


        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        String queue = "tx_queue_a";
        String exchange = "tx_exchange_topic";
        String bindRoutingKey = "q.a";

        // 准备工作
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);//声明交换机
        channel.queueDeclare(queue, false, false, false, null);//声明队列
        channel.queueBind(queue, exchange, bindRoutingKey);//绑定

        try {
            //开启事务模式
            channel.txSelect();
            //发正常消息
            channel.basicPublish(exchange, bindRoutingKey, true, null, "Hello TX!".getBytes(StandardCharsets.UTF_8));
            System.out.println("msg published!");
//            System.out.println(1/0);//exception
            channel.txCommit();
            System.out.println("msg commit!");
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("msg rollback");
        }


//        channel.close();
//        connection.close();
    }
}
