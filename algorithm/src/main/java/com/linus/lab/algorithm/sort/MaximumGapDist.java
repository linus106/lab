package com.linus.lab.algorithm.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/26
 */
public class MaximumGapDist {


    /**
     * 基数排序(二级制版) O(n)
     * 从低位到高位依次进行排序
     * 循环 log2 (max) + 1次
     * [4,3,1,2]         -> [4,2,3,1]         -> [4,1,2,3]         -> [1,2,3,4]
     * [100,011,001,010] -> [100,010,011,001] -> [100,001,010,011] -> [001,010,011,100]
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int max = Arrays.stream(nums).max().getAsInt();
        int[] temp = new int[nums.length];
        int mask = 1;
        while (max >= mask) {
            int zeroCount = 0;
            for (int num : nums) {
                if ((num & mask) == 0) zeroCount++;
            }
            int zeroIndex = 0, oneIndex = zeroCount;
            for (int num : nums) {
                temp[(num & mask) == 0 ? zeroIndex++ : oneIndex++] = num;
            }
            System.arraycopy(temp, 0, nums, 0, nums.length);
            mask = mask << 1;
        }
        return IntStream.range(0, nums.length - 1).map(i-> nums[i+1] - nums[i]).max().getAsInt();
    }

    public static void main(String[] args) {

    }
}
