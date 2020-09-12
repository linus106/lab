package com.linus.lab.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 */
public class ObjectHeader {

    public static SyncHeader o = new SyncHeader();

    /**
     * 64位jvm下的header样例
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *  0     4        (object header)                           01 9d e1 40 (00000001 10011101 11100001 01000000) (1088527617)
     *  4     4        (object header)                           15 00 00 00 (00010101 00000000 00000000 00000000) (21)
     *  8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
     *  12     4    int SyncHeader.a                              0          0
     *
     *  <- 31 bit ->    1540e19d    hashcode 懒计算
     *  <- 1 bit ->     0           unused
     *  <- 4 bit ->     0000        age
     *  <- 1 bit ->     0           whether is biased lock
     *  <- 2 bit ->     01          锁标志位
     */
    private static void objectHeader() {//001
        System.out.println(Integer.toBinaryString(o.hashCode()));//触发hashcode计算
        System.out.println(Integer.toHexString(o.hashCode()));//触发hashcode计算
        System.out.println("----------------------NormalObjectHeader---------------------------");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("----------------------Array Object Header---------------------------");
        System.out.println(ClassLayout.parseInstance(new int[10]).toPrintable());
    }

    public static void main(String[] args) throws InterruptedException {
        objectHeader();
    }
}
