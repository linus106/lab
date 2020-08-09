package com.linus.lab.algrithm.temp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 * https://leetcode-cn.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 * 贪心思想：让每个组合的end尽量的小，让留下的数组的range尽量的大
 * 跟会议安排问题很类似
 */
public class Q3 {

    public static void main(String[] args) {


        int[] a = new int[100];
        System.out.println(maxNonOverlapping(new int[]{1,1,1,1,1}, 2));
    }

    public static int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int sum = 0;//每种组合循环过程中的和
        int count = 0;//几种组合

        for (int n : nums) {
            sum += n;
            if (set.contains(sum - target)) {//sum - target = 前缀和。  说明当前组合存在一种子组合的sum = target
                count++;
                sum = 0;
                set.clear();
                set.add(0);
            } else {
                set.add(sum);
            }
        }
        return count;

    }


}
