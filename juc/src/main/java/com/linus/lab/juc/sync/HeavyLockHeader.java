package com.linus.lab.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 *
 */
public class HeavyLockHeader {
    public volatile static SyncHeader o = new SyncHeader();

    /**
     * 64位jvm下的header样例，只保留markword
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *  0     4        (object header)                           ea 36 5e 1c (11101010 00110110 01011110 00011100) (475936490)
     *  4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *
     *  <- 62 bit ->                指针：指向重量级锁Monitor的指针
     *  <- 2 bit ->     10          锁标志位
     */
    private static void heavyLockHeader() throws InterruptedException {//10
        System.out.println("----------------------HeavyLockHeader---------------------------");

        Thread t = new Thread(()->{
            synchronized (o) {
                System.out.println("heavy lock : first thread hold lock");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(3000);
        synchronized (o) {
            System.out.println("heavy lock : second thread hold lock");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        t.join();
        System.out.println("heavy lock : unlock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Thread.sleep(3000);//时间长是无锁，时间短可能还是重量级锁
        System.out.println("heavy lock : 3s after unlock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());//01

    }


    public static void main(String[] args) throws InterruptedException {
        heavyLockHeader();
    }
}
