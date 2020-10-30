package com.linus.lab.algorithm.trie;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 17:47
 * @Description TODO
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorOfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;

    }


    private class Trie {
        Trie one = null;
        Trie zero = null;
        Integer mask = 0x7fffffff;


        public void insert(int value) {

        }

    }

    public static void main(String[] args) {
        String a = Integer.toBinaryString(25);
        System.out.println(a);

        int mask = 1 << a.length();
        System.out.println(Integer.toBinaryString(mask | 2).substring(1 ));
    }
}
