package com.linus.lab.algorithm.temp.interview;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/9 14:25
 * @Description TODO
 * 我们将石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 每次 move 操作都会移除一块所在行或者列上有其他石头存在的石头。
 * 请你设计一个算法，计算最多能执行多少次 move 操作？
 */
public class Q3 {

    public int removeStones(int[][] stones) {

        Map<Integer, Long> xCountMap = Arrays.stream(stones)
                .collect(Collectors.groupingBy(stone -> stone[0], Collectors.counting()));

        Map<Integer, Long> yCountMap = Arrays.stream(stones)
                .collect(Collectors.groupingBy(stone -> stone[1], Collectors.counting()));

        Arrays.stream(stones).filter(stone->{
            if (xCountMap.get(stone[0]) == 1 && xCountMap.get(stone[0]) == 1) {
                return false;
            }
            return true;
        });

        //TODO??

        return 0;

    }

    public static void main(String[] args) {

    }
}
