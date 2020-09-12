package com.linus.lab.algorithm.temp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 * https://leetcode-cn.com/contest/weekly-contest-202/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class Q9 {

    public List<Integer> mostVisited(int n, int[] rounds) {
        int begin = rounds[0], end = rounds[rounds.length - 1];
        if (end < begin) {
            end += n;
        }
        return Stream.iterate(begin, i -> i + 1).limit(end - begin + 1).map(i -> i > n ? i - n : i).sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        List<Integer> result = new Q9().mostVisited(4, new int[]{1, 3, 1, 2});

        List<Integer> result = new Q9().mostVisited(7, new int[]{1,3,5,7});
        System.out.println(result);
    }

}
