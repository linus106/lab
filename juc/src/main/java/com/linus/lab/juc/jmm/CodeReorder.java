package com.linus.lab.juc.jmm;

import com.linus.lab.juc.jmm.util.UnsafeInstance;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 */
public class CodeReorder {

    private static int x, y, a, b;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            x = 0; y = 0;
            a = 0; b = 0;

            Thread t1 = new Thread(() -> {
                shortWait(20000);
                x = 1;
                UnsafeInstance.reflectGetUnsafe().fullFence();
                //
                b = y;
            });

            Thread t2 = new Thread(() -> {
                y = 1;
                UnsafeInstance.reflectGetUnsafe().fullFence();//

                a = x;
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println(a + ":" + b);
            if (a == 0 && b == 0) {
                return;
            }
        }


    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }


}
