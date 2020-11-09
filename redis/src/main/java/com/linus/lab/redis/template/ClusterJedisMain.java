package com.linus.lab.redis.template;

import com.linus.lab.redis.config.RedisConfig;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/7
 */
public class ClusterJedisMain {

    public static void main(String[] args) throws IOException {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, RedisConfig.REIDS_HOST, RedisConfig.REIDS_PORT_SINGLE_MASTER);



        Set<HostAndPort> jedisClusterNode = Arrays.stream(RedisConfig.REIDS_PORT_CLUSTER)
                .mapToObj(port-> new HostAndPort(RedisConfig.REIDS_HOST, port))
                .collect(Collectors.toSet());



        JedisCluster jedis = null;

        try {
            jedis = new JedisCluster(jedisClusterNode, jedisPoolConfig);

            //general get set
            System.out.println(jedis.set("cluster", new Date().toString()));
            System.out.println(jedis.get("cluster"));

            // lua script - reduce count
            String luaScript = "local count = redis.call('get', KEYS[1]) " +
                    "local curr = tonumber(count) " +
                    "local demands = tonumber(ARGV[1]) " +
                    "if (curr >= demands) then " +
                    "redis.call('set', KEYS[1], curr-demands) " +
                    "return 1 " +
                    "end " +
                    "return 0 ";
            Object res = jedis.eval(luaScript, Arrays.asList("total"), Arrays.asList("100"));
            System.out.println(res);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
}
