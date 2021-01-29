package com.linus.lab.io.netty.codec;

/**
 * @Author wangxiangyu
 * @Date 2021/1/27
 * @Description TODO
 */
public class User {

    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
