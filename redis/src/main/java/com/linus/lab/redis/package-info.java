/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/8
 *
 *
 * 1、数据结构
 * 和java的hashmap基本一样
 *
 * key-String
 * value-string|map|list|set|zset
 *
 * string:sds
 * {
 *     int free:2
 *     int length:4
 *     char[] data
 * }
 * resize: (original + gap ) * 2;大于1m则+1m
 *
 *
 * 2、redis场景
 *      1.1 缓存
 *              1.1.1 事务成功后写，有不一致的风险
 *              1.1.2 异步写,监听binlog,有一定的延迟
 *              1.1.3 分布式会话
 *      1.2 计数器
 *              1.2.1 秒杀，记录库存抗并发，通过incrBy原子操作
 *              1.2.2 分布式id，通过incrby 1000批量获取，保证原子性
 *      1.3 bitmap
 *              1.3.1 记录-统计日活、周活，月活；是否会员等等；除了01状态也可以通过多个位组合的方式表达更多的状态
 *              1.3.2 布隆过滤器，1、新值：信息摘要 | mask = mask；2、判断：信息摘要 & mask == 信息摘要;==可能已经存在,!=不存在
 *      1.4 list
 *              1.4.1 栈、队列
 *              1.4.2 分布式阻塞队列
 *              1.4.3 发布订阅模型
 *      1.5 set|zset
 *              1.5.1 zset-热搜榜、排行榜
 *              1.5.2 set-抽奖、点赞
 *              1.5.3 set-社交推荐模型，差集、交集、并集
 *              1.5.4 zset-延时队列
 *      1.6 分布式锁setnx-SET if Not eXists
 *
 */
package com.linus.lab.redis;