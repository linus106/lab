package com.linus.lab.algorithm.backpack;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/19
 */
public class Bounded {

    public static void main(String[] args) {
        memoryOpt();
    }

    public static void memoryOpt() {

        int[] w = new int[]{2, 3, 4, 5};//重量
        int[] v = new int[]{3, 4, 5, 6};//价值
        int[] limit = new int[]{1, 2, 3, 4};//数量限制
        int c = 8;//背包容量
        int length = w.length;//物品数量

        int[] dp = new int[c + 1];


        for (int i = 0; i < length; i++) {
            for (int j = c; j >= 1; j--) {//逆序
                int kMax = Math.min(limit[i], j / w[i]);
                for (int k = 0; k <= kMax; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                }
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
        int[] limit = new int[]{1, 2, 3, 4};//数量限制
        int c = 8;//背包容量
        int length = w.length;//物品数量

        int[][] dp = new int[length + 1][c + 1];//dp[物品范围][背包容量] = 该约束下的最大价值

        for (int i = 1; i < length + 1; i++) {
            int index = i - 1;
            for (int j = 1; j < c + 1; j++) {
                int max = dp[i - 1][j];
                for (int k = 1; k <= Math.min(limit[index], j / w[index]); k++) {
                    max = Math.max(max, dp[i - 1][j - k * w[index]] + k * v[index]);
                }
                dp[i][j] = max;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(dp[i][j] + "   ");
            }
            System.out.println();
        }


        int[] item = new int[length];
        backTrace(dp, item, w, length, c);

        for (int i : item) {
            System.out.println(i);
        }
    }

    public static void backTrace(int[][] dp, int[] item, int[] w, int i, int c) {
        if (i > 0) {
            if (dp[i][c] == dp[i - 1][c]) {
                backTrace(dp, item, w, i - 1, c);
            } else {
                item[i - 1]++;
                backTrace(dp, item, w, i, c - w[i - 1]);
            }
        }

    }
}
