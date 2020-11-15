package com.linus.lab.redis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/14
 */
@RestController
public class IndexController {

    @Autowired
    private RedissonClient redission;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/api/lock/get")
    public String get(@RequestParam("key") String key) {
        final String s = stringRedisTemplate.opsForValue().get(key);
        System.out.println(s);
        return s;
    }


    /**
     * 简易的分布式锁
     * 1、通过setnx(set if not exist)来进行加锁，设置成功代表获得锁
     * 2、finally中释放锁
     * 3、setnx增加超时时间的参数，防止服务挂掉，兜底锁释放。
     * 4、finally中释放锁需要判断锁是否是当前线程加的
     *
     * 存在问题:
     * 1、锁超时，业务代码仍然执行的问题
     * 2、final中释放锁的判断和delete不是原子性的，理论上还是可能删除掉别线程的锁,应该通过lua脚本保证原子性
     */
    @GetMapping("/api/lock/simple_lock")
    public String simpleLock() {
        String lockKey = "simple_lock";
        String clientId = UUID.randomUUID().toString();
        boolean lockSuccess = false;
        try {
            lockSuccess = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
            if (!lockSuccess) {
                return "please try again";
            }

            //TODO do business
            System.out.println("do business!");
            return "business done!";
        } finally {
            if (lockSuccess && clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                //如果这里出现的延时，理论上仍然可能出删除掉别人锁的情况
                stringRedisTemplate.delete(lockKey);
            }

        }
    }


    /**
     * redission分布式锁-可重入锁
     * 通过hash对象保存
     * "key" : {
     *     "client_id" : "1(锁次数)"
     * }
     *
     * 逻辑：
     * tryLock(lua):
     *      if(not exists key) {    //没redission_lock对象
     *          hincrby key cid 1   //创建hash对象并对cid++表示新加了一次锁
     *          pexpire key 30S     //设置30S到期时间
     *          return nil
     *      } else if (hexists key cid) {   //有没redission_lock对象 并且当前锁的cid和redis里的cid是一样的，说明可以加锁。
     *          hincrby key cid 1
     *          pexpire key 30S
     *          return nil
     *      } else {
     *          return pttl key      //无法执行加锁，返回上一个锁的到期时间
     *      }
     * 如果加锁成功则触发一个1/3到期时间后的定时任务。
     * 通过HashedWheelTimer来执行。
     *
     * 如果没加锁成功则：
     * 1、订阅channel_{key}的消息
     * 2、while (true) {
     *      long ttl = tryAquire();     //尝试获取锁，如果获取不到，返回前任锁的ttl
     *      if (ttl == null) {          //说明获取锁成功
     *          break;
     *      }
     *      if(ttl >= 0) {               //获取锁失败，等待channelFuture的消息，等待的时间是前任锁到期的预期时间。
     *          future.getNow().getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
     *      } else {                    //key 是永久的 -1;-2是不存在key不可能出现,说明key变成永久了的，阻塞等待前任锁到期。
     *          future.getNow().getLatch().acquire();
     *      }
     * }
     *
     *
     * unlock(lua):
     *      if (not exist key) {
     *          return nil
     *      }
     *      counter = hincrby key cid -1      //修改锁状态，解锁1\
     *      if (counter > 0) {
     *          pexpire key 30S
     *          return 0
     *      } else {
     *          del key
     *          publish key_channel 0           //发布事件、通知其他等待者
     *          return 1;
     *      }
     *      return nil;
     * 如果成功，干掉map里的watch dog的timer task，能cancel的要cancel
     *
     */
    @GetMapping("/api/lock/redission_lock")
    public String complexLock() throws InterruptedException {
        String lockKey = "redission_lock";
        final RLock lock = redission.getLock(lockKey);
        try {

            lock.lock();
            //TODO do business
            Thread.sleep(100000);
            System.out.println("do business!");
            return "business done!";
        } finally {
            lock.unlock();
        }
    }


    /**
     * 读写锁
     * 读读不阻塞
     * 写写|读写阻塞
     *
     * 如果一直有读，写会一直阻塞
     *
     *
     *
     *
     *
     *
     */
    @GetMapping("/api/lock/read")
    public String readLock() throws InterruptedException {

        String lockKey = "read_write_lock";
        final RReadWriteLock readWriteLock = redission.getReadWriteLock(lockKey);
        final RLock rLock = readWriteLock.readLock();
        try {
//            System.out.println(Thread.currentThread().getId() + ":" + rLock.getName());
            rLock.lock();
            //TODO do business
            System.out.println(Thread.currentThread().getId() + ":do read!");
//            Thread.sleep(10);
            System.out.println(Thread.currentThread().getId() + ":do read! done!");
            return "read done!";
        } finally {
            rLock.unlock();
        }
    }


    @GetMapping("/api/lock/write")
    public String writeLock() throws InterruptedException {

        String lockKey = "read_write_lock";
        final RReadWriteLock readWriteLock = redission.getReadWriteLock(lockKey);
        final RLock rLock = readWriteLock.writeLock();
        try {
//            System.out.println(Thread.currentThread().getId() + ":" + rLock.getName());
            rLock.lock();
            //TODO do business
            System.out.println(Thread.currentThread().getId() + ":do write!");
//            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getId() + ":do write!done!");
            return "write done!";
        } finally {
            rLock.unlock();
        }
    }


    @GetMapping("/api/lock/segment")
    public String segmentLock() throws InterruptedException {

        //TODO 支持水平扩容的一种实现，分段锁。

        return "";
    }
}
