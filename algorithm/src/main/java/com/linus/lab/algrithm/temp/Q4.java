package com.linus.lab.algrithm.temp;

import java.util.Arrays;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 * https://leetcode-cn.com/problems/minimum-cost-to-cut-a-stick/submissions/
 *
 * 动态规划 + 缓存
 *
 */
public class Q4 {

    public static void main(String[] args) {


        System.out.println(minCost(7,new int[]{1,3,4,5}));
        System.out.println(minCost(9,new int[]{5,6,1,4,2}));

        long start = System.currentTimeMillis();
        int cost = minCost(30,new int[]{13,25,16,20,26,5,27,8,23,14,6,15,21,24,29,1,19,9,3});

        System.out.println(cost);
        System.out.println(System.currentTimeMillis() - start);

    }

    private static int[][] cache = null;

    public static int minCost(int n, int[] cuts) {

        int[] newCuts = Arrays.copyOf(cuts, cuts.length + 2);
        newCuts[newCuts.length - 2] = 0;
        newCuts[newCuts.length - 1] = n;
        Arrays.sort(newCuts);

        cache = new int[newCuts.length][newCuts.length];

        return doMinCost(0, newCuts.length - 1, newCuts);
    }

    public static int doMinCost(int left, int right, int[] cuts) {
        if (right - left <= 1) {
            return 0;
        }
        if (cache[left][right] > 0) {
            return cache[left][right];
        }

        int min = Integer.MAX_VALUE;
        for(int i = left + 1; i < right; i ++) {
            int cost = doMinCost(left, i, cuts) + doMinCost(i, right, cuts) + cuts[right] - cuts[left];
            min = Math.min(cost, min);
        }

        cache[left][right] = min;
        return min;
    }





}
