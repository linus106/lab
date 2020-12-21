package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/18
 * @Description https://leetcode-cn.com/problems/unique-paths/
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsMath(int m, int n) {

        int minMN = Math.min(m, n);
        int res = 1;

        int k = m + n - 2;
        for (int i = 0; i < minMN - 1; i++) {
            res *= (k - i);
            res /= (i + 1);
        }
        return res;

    }

}
