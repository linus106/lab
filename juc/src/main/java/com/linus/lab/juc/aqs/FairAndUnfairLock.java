package com.linus.lab.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/13
 */
public class FairAndUnfairLock {

    private static void fairOrUnfair(boolean fair) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(fair);

        Thread[] threads = new Thread[10];

        for (int i = 0;i < 10;i ++) {
            threads [i] = new Thread(()->{
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "获取了锁");
                } finally {
                    lock.unlock();
                }
            });
        }

        for (int i = 0 ;i < 10;i ++) {
            threads[i].start();
            System.out.println(threads[i].getName() + "启动");
        }
    }


    private static void orderedLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);

        Thread t0 = new Thread(()->{
            try {
                lock.lock();
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("t0收到中断信号");
            } finally {
                lock.unlock();
                System.out.println("t0释放");
            }
        });
        t0.start();

        Thread[] threads = new Thread[10];
        for (int i = 0;i < 10;i ++) {
            threads [i] = new Thread(()->{
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "获取了锁");
                } finally {
                    lock.unlock();
                }
            });
        }

        for (int i = 0 ;i < 10;i ++) {
            threads[i].start();
            System.out.println(threads[i].getName() + "启动");
            Thread.sleep(1000);
        }

        t0.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        fairOrUnfair(true);//启动和获取锁一致
        fairOrUnfair(false);//启动和获取锁顺序可能不一样
        orderedLock();//非公平锁:启动和获取锁顺序一样，说明队列中的顺序一旦确定就不会再竞争

    }
}
