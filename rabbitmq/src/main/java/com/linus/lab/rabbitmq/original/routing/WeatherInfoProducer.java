package com.linus.lab.rabbitmq.original.routing;

import com.linus.lab.rabbitmq.original.util.Consts;
import com.linus.lab.rabbitmq.original.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/27
 */
public class WeatherInfoProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Map<String, String> data = new HashMap(){{
            put("china.beijing","北京晴");
            put("china.shanghai","上海晴");
            put("japan.tokyo","东京阴");
        }};

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String routingKey = entry.getKey();
            String msg = entry.getValue();
            channel.basicPublish(Consts.EXCHANGE_WEATHER_ROUTING, routingKey, null, msg.getBytes(StandardCharsets.UTF_8));
        }

        System.out.println("wether msg  send successs!");

        channel.close();
        connection.close();
    }
}
