package com.linus.lab.rabbitmq.confirm;

import com.linus.lab.rabbitmq.util.Consts;
import com.linus.lab.rabbitmq.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {


        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();


        //开启监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息被broker接受,tag:" + deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息被broker拒绝,tag:" + deliveryTag);
            }
        });
        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return returnMessage) {
                System.out.println("return listener,replyCode:" + returnMessage.getReplyCode());
            }
        });
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("return listener,replyCode:" + replyCode);
            }
        });



//        channel.queueDeclare(Consts.QUEUE_M1, false, false, false, null);

//        channel.basicPublish("", Consts.QUEUE_M1, null, "Hello World!".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish("fim", "", null, "Hello World!".getBytes(StandardCharsets.UTF_8));
        System.out.println("msg send successs!");


//        channel.close();
//        connection.close();
    }
}
