package com.linus.lab.juc.jmm;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 */
public class CodeAtomic {


    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            }).start();
        }
        Thread.sleep(3000);

        System.out.println(count);
    }
}
