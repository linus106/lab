package com.linus.lab.algorithm.dp;

import java.util.Arrays;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/20
 */
public class CoinChange2 {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        if (n == 0) {
            return amount == 0 ? 1 : 0;
        }

        int[][] dp = new int[n][amount + 1];

        //init
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i * coins[0] <= amount; i++) {
            dp[0][i * coins[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][amount];
    }

    public int changeMemoryOpt(int amount, int[] coins) {
        int n = coins.length;
        if (n == 0) {
            return amount == 0 ? 1 : 0;
        }

        //init
        int[] dp = new int[amount + 1];
        for (int i = 0; i * coins[0] <= amount; i++) {
            dp[i * coins[0]] = 1;
        }


        for (int i = 1; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }


}
