package com.linus.lab.algorithm.backpack;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/19
 */
public class ZeroOne {

    public  static void main(String[] args) {
        baseAndBackTrace();
    }

    public static void memoryOpt() {

        int[] w = new int[]{2, 3, 4, 5};//重量
        int[] v = new int[]{3, 4, 5, 6};//价值
        int c = 8;//背包容量
        int length = w.length;//物品数量

        int[] dp = new int[c + 1];


        for (int i = 0; i < length; i++) {
            for (int j = c; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);//逆向遍历
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "   ");
        }
        System.out.println();

    }


    public static void baseAndBackTrace() {

        int[] w = new int[]{2, 3, 4, 5};//重量
        int[] v = new int[]{3, 4, 5, 6};//价值
        int c = 8;//背包容量
        int length = w.length;//物品数量

        int[][] dp = new int[length + 1][c + 1];//dp[物品范围][背包容量] = 该约束下的最大价值

        for (int i = 1; i < length + 1; i++) {
            int index = i - 1;
            for (int j = 1; j < c + 1; j++) {
                if (j < w[index]) {//放不下第i个物品
                    dp[i][j] = dp[i - 1][j];//不放第i个物品
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[index]] + v[index]);//放或者不放两个选择中选一个最优值
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(dp[i][j] + "   ");
            }
            System.out.println();
        }


        int[] item = new int[length];
        int cRemain = 8;
        for (int i = length - 1; i > 0; i--) {
            if (dp[i + 1][cRemain] == dp[i][cRemain]) {
                item[i] = 0;
            } else {
                item[i] = 1;
                cRemain -= w[i];
            }
        }

        for (int i : item) {
            System.out.println(i);
        }


    }
}
