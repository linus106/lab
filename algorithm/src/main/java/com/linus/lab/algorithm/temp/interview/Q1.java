package com.linus.lab.algorithm.temp.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/9 13:47
 * @Description TODO
 *
 * 给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
 * 与 标准答案 误差在 10-5 的结果都被视为正确结果。
 */
public class Q1 {

    public double trimMean(int[] arr) {
        int length = arr.length;
        int k = length / 20;

        PriorityQueue<Integer> maxK = new PriorityQueue(k);


        List<Integer> min95 = new ArrayList<>();
        for (int n : arr) {
            if (maxK.size() < k) {
                maxK.offer(n);
            } else {
                if (maxK.peek() < n){
                    min95.add(maxK.poll());
                    maxK.offer(n);
                } else {
                    min95.add(n);
                }
            }


        }

        List<Integer> mid90 = new ArrayList<>();
        PriorityQueue<Integer> minK = new PriorityQueue<Integer>(k, (a,b)->b-a);
        for (Integer n : min95) {
            if (minK.size() < k) {
                minK.offer(n);
            } else {
                if (minK.peek() > n){
                    mid90.add(minK.poll());
                    minK.offer(n);
                } else {
                    mid90.add(n);
                }
            }
        }

        double avg = mid90.stream().mapToInt(Integer::intValue).average().getAsDouble();
        return avg;
    }

    public static void main(String[] args) {
        double res = new Q1().trimMean(new int[]{6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,
                10,8,
                2,3,4});

        System.out.println(res);
    }
}
