package com.linus.lab.redis.template;

import com.linus.lab.redis.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.Date;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/7
 */
public class JedisMain {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, RedisConfig.REIDS_HOST, RedisConfig.REIDS_PORT_SINGLE_MASTER);

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            //general get set
            System.out.println(jedis.set("test", new Date().toString()));
            System.out.println(jedis.get("test"));

            //pipeline
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0;i<10;i++) {
                pipelined.set("test" + i, new Date().toString());
                pipelined.incrBy("total", 1);
            }
            System.out.println(pipelined.syncAndReturnAll());

            // lua script - reduce count
            String luaScript = "local count = redis.call('get', KEYS[1]) " +
                    "local curr = tonumber(count) " +
                    "local demands = tonumber(ARGV[1]) " +
                    "if (curr >= demands) then " +
                    "redis.call('set', KEYS[1], curr-demands) " +
                    "return 1 " +
                    "end " +
                    "return 0 ";
            Object res = jedis.eval(luaScript, Arrays.asList("total"), Arrays.asList("6"));
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
