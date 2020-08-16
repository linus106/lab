package com.linus.lab.algorithm.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 * https://leetcode-cn.com/contest/weekly-contest-202/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class Q8 {

    private Map<Integer,Integer> map = new HashMap<>();

    public int minDaysFinal(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        Integer cached = map.get(n);
        if (cached != null) return cached;

        int m2 = minDaysFinal(n/2) + n%2 + 1;
        int m3 = minDaysFinal(n/3) + n%3 + 1;
        int result = Math.min(m2, m3);
        map.put(n, result);
        return result;
    }


    public int minDaysOpt(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        LinkedList<Integer> queue = new LinkedList();
        queue.add(1);
        while (true) {
            int i = queue.poll();
            int x = i + 1, y = 2 * i, z = 3 * i;
            for (int next : Arrays.asList(x, y, z)) {
                if (next == n) {
                    return dp[i] + 1;
                }
                if (next < n && dp[next] == 0) {
                    dp[next] = dp[i] + 1;
                    queue.add(next);
                }
            }
        }
    }

    public int minDays(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            int x = i + 1, y = 2 * i, z = 3 * i;
            for (int next : Arrays.asList(x, y, z)) {
                if (next <= n) {
                    if (dp[next] == 0) {
                        dp[next] = dp[i] + 1;
                    } else {
                        dp[next] = Math.min(dp[i] + 1, dp[next]);
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int result = new Q8().minDaysOpt(6128305);
        System.out.println(result);
    }

}
