package com.linus.lab.rabbitmq;

import com.linus.lab.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tester {

    @Autowired
    private RabbitTemplate rabbitTemplate;


//    @Test
//    public void send() {
//        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "boot.test", "yaha!".getBytes());
//        System.out.println(1);
//    }

    @Test
    public void testConfirm() throws InterruptedException {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(@Nullable CorrelationData correlationData, boolean b, @Nullable String s) {
                System.out.println("confirm called");
                if (b) {
                    System.out.println("receive msg succ:" + s);
                } else {
                    System.out.println("receive msg fail:" + s);
                }
            }
        });
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME + 1, "boot.test", "yaha!".getBytes());

        Thread.sleep(100000);
    }
}
