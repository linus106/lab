package com.linus.lab.algorithm.reservoir;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/8/7 11:49
 * @Description TODO
 * https://leetcode-cn.com/problems/random-pick-index/
 * LeetCode398
 * 给定一个可能含有重复元素的整数数组，要求随机(等概率)输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 */
public class RandomPickIndex {

    private int[] nums;


    public RandomPickIndex(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int index = 0;
        int candidateCount = 0;
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                if (candidateCount == 0) {
                    index = i;
                } else {
                    boolean replace = random.nextInt(candidateCount + 1) == 0;
                    if (replace) {
                        index = i;
                    }
                }
                candidateCount ++;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Map<Integer, Long> statisticsResult = Stream.iterate(0, i->i+1).limit(10000).map(i->{
            int result = new RandomPickIndex(new int[]{1,2,3,3,3}).pick(3);
            return result;
        }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        statisticsResult.forEach((i,count)->{
            System.out.println(String.format("%d-%d",i,count));
        });
    }
}
