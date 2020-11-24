package com.linus.lab.algorithm.doublepointer;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/23
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int val = nums[0];
        int toWhere = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != val){
                val = nums[i];
                nums[toWhere++] = val;
            }
        }
        return toWhere;

    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray o = new RemoveDuplicatesFromSortedArray();
        int res = o.removeDuplicates(new int[]{1,1,2});
        System.out.println(res);

    }
}
