package com.linus.lab.redis.flash;

import com.linus.lab.redis.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/8
 */
public class OrderService {

    private OrderDao orderDao;

    private JedisPool jedisPool;

    private ThreadLocal<Jedis> jedisThreadLocal = new ThreadLocal<>();

    public OrderService() {
        this.orderDao = new OrderDao();

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(100);
        this.jedisPool = new JedisPool(jedisPoolConfig, RedisConfig.REIDS_HOST, RedisConfig.REIDS_PORT_SINGLE_MASTER);
    }

    /**
     * 1、jedis.get抗10W并发，如果没库存了直接失败
     * 2、jedis.incrBy(-1)，原子扣-1，如果<0，则失败
     * 3、如果>=0则往db下单
     * 4、finally，如果扣过库存，且没往db下单，则把库存加回来。
     *
     * 5、对账任务，对比db和redis库存，如果对不上则补。
     *
     */
    public boolean order(Integer productId, Integer userId) {

        String stockKey = "stock_" + productId;
        boolean redisDedect = false;
        int res = 0;
        Jedis jedis = jedisThreadLocal.get();
        if (jedis == null) {
            jedis = jedisPool.getResource();
            jedisThreadLocal.set(jedis);
        }
        try {
            Integer stock = Integer.valueOf(jedis.get(stockKey));
            if (stock <= 0) {
                return false;
            }

            stock = jedis.incrBy(stockKey, -1).intValue();
            redisDedect = true;
            if (stock < 0) {
                return false;
            }
            res = orderDao.insert(productId, userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (redisDedect && res!=1) {
                jedis.incrBy(stockKey, 1);
            }
        }

    }
}
