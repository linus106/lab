package com.linus.lab.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/2
 */
@Component
public class RabbitmConsumer {

    @RabbitListener(queues = "boot_queue")
    public void listerQueue(Message message) {

        System.out.println(new String(message.getBody()));

    }
}
