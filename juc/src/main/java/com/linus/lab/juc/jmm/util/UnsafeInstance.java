package com.linus.lab.juc.jmm.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}