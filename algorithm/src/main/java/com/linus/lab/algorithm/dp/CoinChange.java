package com.linus.lab.algorithm.dp;

import java.util.Arrays;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/19
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        //init
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1, amount + 1, Integer.MAX_VALUE);
        }
        for (int i = 1; i * coins[0] <= amount; i++) {
            dp[0][i * coins[0]] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i] && dp[i][j - coins[i]] < dp[i][j] - 1) {
                    dp[i][j] = dp[i][j - coins[i]] + 1;
                }
//                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);// avoid overflow
            }
        }
        return dp[n - 1][amount] == Integer.MAX_VALUE ? -1 : dp[n - 1][amount];
    }

    public int coinChangeMemoryOpt(int[] coins, int amount) {
        int n = coins.length;

        int[] dp = new int[amount + 1];

        //init
        Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (j >= coins[i] && dp[j - coins[i]] < dp[j] - 1) {
                    dp[j] = dp[j - coins[i]] + 1;
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {

    }

}
