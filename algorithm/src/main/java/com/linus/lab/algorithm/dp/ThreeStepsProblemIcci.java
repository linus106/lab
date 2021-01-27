package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/24
 * @Description TODO
 */
public class ThreeStepsProblemIcci {

    public int waysToStep(int n) {

        int[] dp = new int[Math.max(n + 1, 3)];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % 1000000007 + dp[i - 3]) % 1000000007;
        }
        return Long.valueOf(dp[n]).intValue();
    }

    public static void main(String[] args) {
        System.out.println(new ThreeStepsProblemIcci().waysToStep(5));
    }
}
