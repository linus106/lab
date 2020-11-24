package com.linus.lab.algorithm.doublepointer;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/23
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        int l = 0, r = nums.length - 1;
        int n = 0;
        while (r >= 0 && nums[r] == val) {
            r--;
            n++;
        }
        while (l < r) {
            if (nums[l] == val) {
                swap(nums, r, l);
                while (r >= 0 && nums[r] == val) {
                    r--;
                    n++;
                }
            }
            l++;
        }
        return nums.length - n;
    }

    private void swap(int[] nums, int m, int n) {
        int temp = nums[n];
        nums[n] = nums[m];
        nums[m] = temp;
    }

    public static void main(String[] args) {

    }
}
