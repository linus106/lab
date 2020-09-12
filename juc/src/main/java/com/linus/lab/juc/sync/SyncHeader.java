package com.linus.lab.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 *
 * 0 01:无锁
 * 1 01:偏向锁
 *   00:轻量级锁
 *   10:重量级锁
 */
public class SyncHeader {

    public int a;

    public static void main(String[] args) throws InterruptedException {
        ObjectHeader.main(args);
        BiasedLockHeader.main(args);
        LightLockHeader.main(args);
        HeavyLockHeader.main(args);
    }
}
