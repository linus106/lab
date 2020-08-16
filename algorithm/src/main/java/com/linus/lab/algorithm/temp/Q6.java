package com.linus.lab.algorithm.temp;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 */
public class Q6 {

    public int minOperations(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return minOperations(n-2) + n -1;

    }

    public static void main(String[] args) {

    }
}
