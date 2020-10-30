package com.linus.lab.juc.coll;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiangyu
 * @Date 2020/10/29 11:52
 * @Description TODO
 */
public class HashMapInspect {


    public static void main(String[] args) {


        Map<Integer, String> map = new HashMap<>();

        for (int i = 0 ;i < 9;i++) {//同slot下第九个元素会触发treeifyBin，把链表转为红黑树
            int key = 1<<(4 + i);
            map.put(key, "");
//            System.out.println(15 & hash(key));
        }

        Map<Integer, String> otherMap = new HashMap<>();
        for (int i = 0 ;i < 13;i++) {//resize, slot数达到1 << 30时，把阈值调整到Integer.Max不再扩容，
            int key = 1<<(4 + i);
            otherMap.put(key, "");
        }

        System.out.println(1 << 30);
        System.out.println(Integer.MAX_VALUE);

    }

    static int hash(Integer key) {
        int h;
        return (key == null) ? 0 : (h = key) ^ (h >>> 16);
    }
}
