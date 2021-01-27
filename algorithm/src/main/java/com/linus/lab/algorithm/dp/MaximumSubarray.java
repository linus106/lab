package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/24
 * @Description TODO
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] sums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
        }

        int res = Integer.MIN_VALUE;
        int min = 0;
        for (int i = 0; i < sums.length; i++) {
            res = Math.max(sums[i] - min, res);
            min = Math.min(min, sums[i]);
        }
        return res;
    }



    public static void main(String[] args) {
        MaximumSubarray o = new MaximumSubarray();
        int res = o.maxSubArray(new int[]{-1, -2});
        System.out.println(res);

    }
}
