package com.linus.lab.algorithm.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/19
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        // pre condition
        if (nums.length < 1) return false;
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >> 1;

        boolean[][] dp = new boolean[nums.length][half + 1];


        // init
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= half) {
            dp[0][nums[0]] = true;
        }

        // zero-one pack (filled full)
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= half; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][half];
    }

    public boolean canPartitionMemroyOpt(int[] nums) {
        // pre condition
        if (nums.length < 1) return false;
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >> 1;

        boolean[] dp = new boolean[half + 1];


        // init
        dp[0] = true;


        // zero-one pack (filled full)
        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[half];
    }



    public static void main(String[] args) {

    }
}
