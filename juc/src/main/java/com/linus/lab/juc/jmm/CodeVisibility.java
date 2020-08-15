package com.linus.lab.juc.jmm;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/7
 */
public class CodeVisibility {

    private static volatile boolean flag = true;

    private static Integer count = 0;

    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            while (flag) {
                count++;// do stm 可能会导致CPU让出时间片，从而重新读到flag的新值
            }
            System.out.println("t1 end" + count);
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            flag = false;
            System.out.println("t2 end");
        }).start();
    }
}
