package com.linus.lab.algorithm.trie;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 17:47
 * @Description TODO
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorOfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {

        int maxNum = IntStream.of(nums).max().getAsInt();
        int l = Integer.toBinaryString(maxNum).length();//计算最大长度

        int max = 0,curr = 0;//max:实际最大异或值,curr:可能最大异或值
        for (int i = l - 1; i >= 0; i--) {//从1->l位开始遍历
            max = max << 1;//10101  -> 101010
            curr = max | 1;//101011

            Set<Integer> prefixSet = new HashSet<>();
            for (int num : nums) {// 第1位:10101->1;第2位:10101->10;第3位:10101->101
                prefixSet.add(num >> i);
            }

            for (Integer prefix : prefixSet) {
                if (prefixSet.contains(curr ^ prefix)) {//判断num前缀中能不能找到两个，他们的异或值等于可能的最大异或值。
                    max = curr;
                    break;
                }
            }
        }

        return max;
    }

    public int findMaximumXORByTire(int[] nums) {
        int maxNum = IntStream.of(nums).max().getAsInt();
        int l = Integer.toBinaryString(maxNum).length();//计算最大长度
        int bitMask = 1 << l;
        Trie root = new Trie();

        for (int num : nums) {
            String numStr = Integer.toBinaryString(num | bitMask).substring(1);
            root.insert(numStr);
        }
        String reusltStr = dfs(root, root);

        return Integer.valueOf(reusltStr, 2);
    }

    public String dfs(Trie left, Trie right) {
        if (left.one == null && left.zero == null) return "";
        Character currentMax = '0';
        String suffixMax = "";
        boolean l1r0 = left.one != null && right.zero != null;
        boolean l0r1 = left.zero != null && right.one != null;
        boolean l1r1 = left.one != null && right.one != null;
        boolean l0r0 = left.zero != null && right.zero != null;
        boolean sameRoot = left == right;


        if (l1r0 || l0r1) {
            currentMax = '1';
            if (l1r0) suffixMax = dfs(left.one, right.zero);
            if (l0r1 && !sameRoot) {
                String value = dfs(left.zero, right.one);
                if (value.compareTo(suffixMax) > 0) suffixMax = value;
            }
        } else {
            currentMax = '0';
            if (l1r1) suffixMax = dfs(left.one, right.one);
            if (l0r0 && !sameRoot) {
                String value = dfs(left.zero, right.zero);
                if (value.compareTo(suffixMax) > 0) suffixMax = value;
            }
        }
        return currentMax + suffixMax;
    }


    private class Trie {
        Trie one = null;
        Trie zero = null;

        public void insert(String str) {
            if (str.length() < 1) return;

            Character c = str.charAt(0);
            String sub = str.substring(1);

            if (c.equals('1')) {
                if (one == null) one = new Trie();
                one.insert(sub);
            } else {
                if (zero == null) zero = new Trie();
                zero.insert(sub);
            }
        }
    }

    public static void main(String[] args) {
//        String a = Integer.toBinaryString(25);
//        System.out.println(a);
//
//        int mask = 1 << a.length();
//        System.out.println(Integer.toBinaryString(mask | 2).substring(1 ));

//        System.out.println(Integer.toBinaryString(1 << 30));

        int result = new MaximumXorOfTwoNumbersInAnArray().findMaximumXORByTire(new int[]{1,25});
        System.out.println(result);

    }
}
