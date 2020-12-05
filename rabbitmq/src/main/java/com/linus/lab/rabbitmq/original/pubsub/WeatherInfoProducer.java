package com.linus.lab.rabbitmq.original.pubsub;

import com.linus.lab.rabbitmq.original.util.Consts;
import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class WeatherInfoProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();


        channel.basicPublish(Consts.EXCHANGE_WEATHER, "", null, input.getBytes(StandardCharsets.UTF_8));
        System.out.println("wether msg  send successs!");


        channel.close();
        connection.close();
    }
}
