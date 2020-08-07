package com.linus.lab.concurrency.jmm;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/5
 */
public class CodeVisibility {


    private static boolean flag = false;

    private static int count = 0;

    public static void main(String[] args) {


        new Thread(()->{
            while(!flag) {
//                count++; //可能导致CPU切换，从而重新加载flag的值
            }
            System.out.println("t1 finished");
        }).start();


        new Thread(()->{
            flag = true;
            System.out.println("t2 change flag");
        }).start();

//        System.out.println(count);

    }
}
