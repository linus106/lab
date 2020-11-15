package com.linus.lab.redis.lock;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/14
 */
public class HashedWheelTimerTest {

    public static void main(String[] args) {
        //普通timer， 基于优先队列，单线程循环从队列中尝试获取任务。
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("jdk timer done");
            }
        }, 10000);



        //netty实现的时间轮timer，精度差一些，但是任务堆积情况下的延迟率低很多。
        //这个不难理解，delayqueue的做法是循环的poll，每次都需要重新的堆排序。如果同一时间的任务很多，那么延迟是不可避免的。
        //时间轮的数据结构，可以保证到时间了，链表的结构可以保证调度线程第一时间把任务分发给工作线程
        final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

        hashedWheelTimer.start();
        hashedWheelTimer.newTimeout(new io.netty.util.TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("hashedWheel done");
            }
        }, 10, TimeUnit.SECONDS);

    }
}
