package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/1 18:30
 * @Description TODO
 */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        int[][] dp = new int[nums.length][nums.length];//记录范围和
        int length = 1;
        while (length <= nums.length) {
            for (int i = 0; i + length - 1 < nums.length; i++) {
                int end = i +length - 1;
                if (length == 1) {
                    dp[i][end] = nums[i];
                } else {
                    dp[i][end] = dp[i][end - 1] + nums[end];
                    if (isNTimes(dp[i][end], k)) return true;
                }
            }
            length++;
        }
        return false;
    }

    private boolean isNTimes(int value, int k) {
        if (k == value) return true;
        if (k == 0) return false;
        return value % k == 0;
    }

    public static void main(String[] args) {

        ContinuousSubarraySum o = new ContinuousSubarraySum();
        boolean res = o.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6);
        System.out.println(res);

    }
}
