package com.linus.lab.algorithm.array;

/**
 * @Author wangxiangyu
 * @Date 2020/12/18
 * @Description https://leetcode-cn.com/problems/richest-customer-wealth/
 */
public class RichestCustomerWealth {

    public int maximumWealth(int[][] accounts) {

        int max = 0;

        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                sum += accounts[i][j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
