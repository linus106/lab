package com.linus.lab.algorithm.trie;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 17:19
 * @Description TODO
 * https://leetcode-cn.com/problems/map-sum-pairs/
 */
public class MapSumPairs {

    static class MapSum {

        int val;
        MapSum[] subs;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            val = 0;
            subs = new MapSum[26];
        }

        public void insert(String key, int val) {
            if (key.length() == 0) {
                this.val = val;
                return;
            }

            char firstChar = key.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) subs[index] = new MapSum();

            subs[index].insert(key.substring(1), val);
        }

        public int sum(String prefix) {
            if (prefix.length() == 0) {
                return dfsSum();
            }

            char firstChar = prefix.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) subs[index] = new MapSum();

            return subs[index].sum(prefix.substring(1));
        }

        public int dfsSum() {
            return this.val + Arrays.stream(subs).filter(map -> map != null).mapToInt(MapSum::dfsSum).sum();
        }
    }


    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("apple", 3);
        System.out.println(obj.sum("ap"));
        obj.insert("app", 2);
        System.out.println(obj.sum("ap"));
    }

}
