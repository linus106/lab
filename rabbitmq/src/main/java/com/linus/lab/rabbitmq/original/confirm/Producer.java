package com.linus.lab.rabbitmq.original.confirm;

import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.*;

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


        //开启监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("异步confirm成功,tag:" + deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("异步confirm失败,tag:" + deliveryTag);
            }
        });
        channel.addReturnListener(returnMessage -> System.out.println("消息return callback,replyCode:" + returnMessage.getReplyText()));

        String queue = "confirm_queue_a";
        String exchange = "confirm_exchange_topic";
        String bindRoutingKey = "q.a";

        // 准备工作
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);//声明交换机
        channel.queueDeclare(queue, false, false, false, null);//声明队列
        channel.queueBind(queue, exchange, bindRoutingKey);//绑定

        //发正常消息
        channel.basicPublish(exchange, bindRoutingKey, true, null, "Hello Confirm!".getBytes(StandardCharsets.UTF_8));
        System.out.println("msg published!");

        //发routingkey未绑定的消息
        channel.basicPublish(exchange, bindRoutingKey + 1, true, null, "Hello Confirm!".getBytes(StandardCharsets.UTF_8));
        System.out.println("msg published!");

//        // 同步confirm
//        channel.waitForConfirmsOrDie();
//        System.out.println("confirmed !");


//        channel.close();
//        connection.close();
    }
}
