package com.linus.lab.jvm.proxy;

/**
 * @Author wangxiangyu
 * @Date 2020/10/30 18:06
 * @Description TODO
 */
public class Target implements IRun, IWalk {

    @Override
    public void run() {
        System.out.println("target run!");
    }

    @Override
    public void walk() {
        System.out.println("target walk!");
    }
}
