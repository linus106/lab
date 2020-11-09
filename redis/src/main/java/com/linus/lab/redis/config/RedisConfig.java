package com.linus.lab.redis.config;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/7
 */
public class RedisConfig {

    public static final String REIDS_HOST = "192.168.0.101";

    public static final int REIDS_PORT_SINGLE_MASTER = 6379;

    public static final int REIDS_PORT_SINGLE_SLAVE = 6380;

    public static final int[] REIDS_PORT_CLUSTER = new int[]{6381, 6382, 6383, 6384, 6385, 6386};
}
