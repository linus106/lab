package com.linus.lab.algorithm.array;

/**
 * @Author wangxiangyu
 * @Date 2020/12/18
 * @Description TODO
 */
public class NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {
        if (nums.length == 0) return true;

        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                if (count == 1) return false;
                if (i >= 2 && nums[i - 2] > nums[i]) {// change num[i] to prev
                    nums[i] = nums[i - 1];
                }
                count++;
            }
        }
        return true;

    }

    public static void main(String[] args) {

    }
}
