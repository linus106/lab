package com.linus.lab.algorithm.array;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/11/27 16:29
 * @Description TODO
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {


    /**
     *                 toswap
     *                  |
     *              4   |
     *                  3
     *           2          2
     *       1   |
     *    0      |
     *          cut
     *
     *
     *    0  1   3  2  2  4
     */
    public void nextPermutation(int[] nums) {
        int cut = nums.length - 2;//断层位置：从后往前看，第一下降的位置
        while (cut >= 0 && nums[cut] >= nums[cut + 1]) {
            cut--;
        }
        if (cut >= 0) {
            int toSwap = nums.length - 1;
            while (toSwap >= 0 && nums[toSwap] <= nums[cut]) {
                toSwap--;
            }
            swap(nums, cut, toSwap);
        }
        reverse(nums, cut + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        NextPermutation o = new NextPermutation();
        int[] nums = new int[]{3,2,1};
        o.nextPermutation(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
