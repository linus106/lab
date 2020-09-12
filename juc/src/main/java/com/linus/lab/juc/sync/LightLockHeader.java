package com.linus.lab.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 *
 */
public class LightLockHeader {
    public static SyncHeader o = new SyncHeader();


    /**
     * 64位jvm下的header样例，只保留markword
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *  0     4        (object header)                           48 f3 90 20 (01001000 11110011 10010000 00100000) (546370376)
     *  4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *
     *  <- 62 bit ->                指针：指向线程栈中的锁指针的记录
     *  <- 2 bit ->     00          锁标志位
     */
    private static void lightLockHeader() throws InterruptedException {//00
        System.out.println("----------------------LightLockHeader---------------------------");
        synchronized (o) {
            System.out.println("light lock : first thread hold lock");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());//可能是轻量级锁，或者偏向锁
        }

        Thread t = new Thread(()->{
            synchronized (o) {
                System.out.println("light lock : second thread hold lock");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());//轻量级锁
            }
        });
        Thread.sleep(3000);//保证t在main后执行
        t.start();
        t.join();

        System.out.println("unlock:");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());//unlock状态，markword变为无锁
    }


    public static void main(String[] args) throws InterruptedException {
        lightLockHeader();
    }
}
