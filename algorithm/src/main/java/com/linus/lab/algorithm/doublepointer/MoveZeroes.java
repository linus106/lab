package com.linus.lab.algorithm.doublepointer;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/11/19 10:25
 * @Description TODO
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums [i] != 0) {
                nums[p++] = nums[i];
            }
        }
        for (int i = p; i < nums.length; i++) {
            nums [i] = 0;
        }
    }


    private void swap(int[] nums,int  m,int  n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public static void main(String[] args) {
        MoveZeroes o = new MoveZeroes();
        int[] input = new int[]{0,1,0,3,12};
        o.moveZeroes(input);

        for (int i : input) {
            System.out.println(i);
        }
    }
}
