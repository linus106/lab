package com.linus.lab.algorithm.dp;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/12/3 17:54
 * @Description TODO
 * https://leetcode-cn.com/problems/count-sorted-vowel-strings
 */
public class CountSortedVowelStrings {

    public int countVowelStringsDp(int n) {
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 1][0];
            dp[i][2] = dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
            dp[i][3] = dp[i - 1][3] + dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
            dp[i][4] = dp[i - 1][4] + dp[i - 1][3] + dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
        }
        return dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2] + dp[n - 1][3] +dp[n - 1][4];
    }

    public int countVowelStrings(int n) {//C n + 4, 4
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }

    public static void main(String[] args) {


    }
}
