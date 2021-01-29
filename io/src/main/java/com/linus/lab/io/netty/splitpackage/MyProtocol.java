package com.linus.lab.io.netty.splitpackage;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2021/1/27
 * @Description TODO
 */
public class MyProtocol {

    private int lenth;

    private byte[] data;

    public MyProtocol(int lenth, byte[] data) {
        this.lenth = lenth;
        this.data = data;
    }

    public int getLenth() {
        return lenth;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        try {
            return "MyProtocol{" +
                    "lenth=" + lenth +
                    ", data=" + new String(data, "UTF-8") +
                    '}';
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
