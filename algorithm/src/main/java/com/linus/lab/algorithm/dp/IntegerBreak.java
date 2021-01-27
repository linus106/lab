package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/21
 * @Description TODO
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * (i - j));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak o = new IntegerBreak();
        int res = o.integerBreak(4);
        System.out.println(res);
    }
}
