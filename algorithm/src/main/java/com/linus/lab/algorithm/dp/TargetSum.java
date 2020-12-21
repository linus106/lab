package com.linus.lab.algorithm.dp;

import java.util.Arrays;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/20
 * https://leetcode-cn.com/problems/target-sum/submissions/
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        final int sum = Arrays.stream(nums).sum();
        final int sum2Match = sum - Math.abs(S);
        if (sum < Math.abs(S) || (sum2Match & 1) == 1) {
            return 0;
        }
        int target = sum2Match >> 1;

        int[][] dp = new int[nums.length][target + 1];

        //init
        dp[0][0] = 1 + (nums[0] == 0 ? 1 : 0);


        if (nums[0] >= 1 && nums[0] <= target) {
            dp[0][nums[0]] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp[i][0] = 2 * dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }


        //0-1 package (m)
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i-1][j] + dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public static void main(String[] args) {

        TargetSum o = new TargetSum();
        final int res = o.findTargetSumWays(new int[]{1,2,1}, 0);
        System.out.println(res);
    }
}
