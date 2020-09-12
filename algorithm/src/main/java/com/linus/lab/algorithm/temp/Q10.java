package com.linus.lab.algorithm.temp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 * https://leetcode-cn.com/contest/weekly-contest-202/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class Q10 {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int sum = 0;
        for (int i = piles.length - 2; i >= piles.length / 3; i = i - 2) {
            sum += piles[i];
        }
        return sum;
    }


    public static void main(String[] args) {
//        List<Integer> result = new Q9().mostVisited(4, new int[]{1, 3, 1, 2});

        int result = new Q10().maxCoins(new int[]{});
        System.out.println(result);
    }

}
