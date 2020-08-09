package com.linus.lab.algrithm.temp;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(findKthBit(2,3));


    }

    public static char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int lenth = 1<<n;
        int mid = (lenth + 1) / 2;
        if (mid == k) {
            return '1';
        }

        if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            return invertSingle(findKthBit(n - 1, lenth - k));
        }
    }

    public static char invertSingle(char c) {
        if (c == '0') {
            return '1';
        } else {
            return '0';
        }
    }

}
