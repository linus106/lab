package com.linus.lab.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/2
 */
@Component
public class AckLisnter implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        System.out.println("message id :" + message.getMessageProperties().getDeliveryTag());
        System.out.println(new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

    }
}
