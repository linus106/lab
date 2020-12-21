package com.linus.lab.algorithm.dp;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/20
 */
public class CombinationSumIV {

    // 顺序敏感，涉及排列组合；更像爬楼梯问题
    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];

    }

    // 顺序不敏感 = 完全背包
    public int combinationSum4Old(int[] nums, int target) {
        int[][] dp = new int[nums.length][target + 1];

        //init
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i * nums[0] <= target; i++) {
            dp[0][i * nums[0]] = 1;
        }


        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }


    public static void main(String[] args) {
        CombinationSumIV o = new CombinationSumIV();
        final int res = o.combinationSum4(new int[]{1, 2, 3}, 4);
        System.out.println(res);
    }
}
