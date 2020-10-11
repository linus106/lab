package com.linus.lab.juc.aqs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/13
 */
public class LockStructure {


    private static void printLock(ReentrantLock lock) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field field = Class.forName("java.util.concurrent.locks.ReentrantLock").getDeclaredField("sync");
        field.setAccessible(true);
        AbstractQueuedSynchronizer aqs = (AbstractQueuedSynchronizer)field.get(lock);

        Method method = Class.forName("java.util.concurrent.locks.AbstractQueuedSynchronizer").getDeclaredMethod("getState");
        method.setAccessible(true);
        System.out.println("state: " + method.invoke(aqs));


    }


    //TODO 同步队列 条件队列
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {

        ReentrantLock lock = new ReentrantLock();
        printLock(lock);
        lock.lock();
        lock.lock();
        printLock(lock);
        lock.unlock();
        printLock(lock);

    }
}
