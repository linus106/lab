package com.linus.lab.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 *
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 */
public class BiasedLockHeader {
    public static SyncHeader o = new SyncHeader();


    /**
     * 64位jvm下的header样例，只保留markword
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *  0     4        (object header)                           05 e0 01 03 (00000101 11100000 00000001 00000011) (50454533)
     *  4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *
     *  <- 54 bit ->                thread id -- 如何证明?
     *  <- 2 bit ->     00          Epoch -- 何用?
     *  <- 1 bit ->     0           unused
     *  <- 4 bit ->     0000        age
     *  <- 1 bit ->     1           whether is biased lock
     *  <- 2 bit ->     01          锁标志位
     */
    private static void biasedLockHeader() throws InterruptedException {//101
//        Thread.sleep(5 * 1000);//从jvm启动，偏向锁需要一定实现才会生效，因为jvm启动过程有所竞争的情况
        System.out.println("----------------------BiasedLockHeader---------------------------");
        System.out.println("thread id : " + Thread.currentThread().getId());

        System.out.println("default biased header:");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());//匿名偏向
        synchronized (o) {
            System.out.println("truely biased header:");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());//真正偏向
        }
        Thread.sleep(3000);
        System.out.println("unlock:");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());//unlock状态，markword不变

        Thread t = new Thread(()->{
            synchronized (o) {
                System.out.println("biased lock : second thread hold lock");//轻量级锁
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        t.start();
        t.join();

        System.out.println("second thread unlock:");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());//无锁状态

    }


    public static void main(String[] args) throws InterruptedException {
        biasedLockHeader();
    }
}
