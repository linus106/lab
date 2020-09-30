package com.linus.lab.algorithm.sampling_random;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/8/10 14:31
 * @Description TODO
 * 尽量减少rand7的调用测试
 * 策略一：10次
 * 策略二：丢弃法:7*7=49;使用40,丢弃9,循环; 49/40 * 2次 (等比数列)
 * 策略三：重复利用法 49-40=9 -> 63-60=3 -> 21-20=1
 * https://leetcode-cn.com/problems/implement-rand10-using-rand7/
 */
public class ImplementRand10UsingRand7 {

    public int rand10Basic() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += rand7();
        }
        return sum % 10 + 1;
    }

    public int rand10ByDrop() {
        while (true) {
            int val = 7 * (rand7() - 1) + rand7();
            if (val <= 40) {
                return val % 10 + 1;
            }
        }
    }

    public int rand10() {
        while (true) {
            int val = 7 * (rand7() - 1) + rand7();
            if (val <= 40) {
                return val % 10 + 1;
            }

            val = 7 * (val - 40 - 1) + rand7();
            if (val <= 60) {
                return val % 10 + 1;
            }

            val = 7 * (val - 60 - 1) + rand7();
            if (val <= 20) {
                return val % 10 + 1;
            }
        }
    }

    public int rand7() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }

    public static void main(String[] args) {

        Map<Integer, Long> statisticsResult = Stream.iterate(0, i -> i + 1).limit(10000).map(i -> {
            int result = new ImplementRand10UsingRand7().rand10();
            return result;
        }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        statisticsResult.forEach((i, count) -> {
            System.out.println(String.format("%d-%d", i, count));
        });


    }
}
