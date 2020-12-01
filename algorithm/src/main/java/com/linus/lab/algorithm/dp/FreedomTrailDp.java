package com.linus.lab.algorithm.dp;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 18:22
 * @Description TODO
 */
public class FreedomTrailDp {


    public int findRotateSteps(String ring, String key) {
        int[][] dp = new int[key.length()][ring.length()];
        List<Integer>[] bucket = new ArrayList[26];
        for (int i = 0; i < ring.length(); i++) {
            int index = ring.charAt(i) - 'a';
            if (bucket[index] == null) bucket[index] = new ArrayList<>();
            bucket[index].add(i);
        }

        for (Integer offset : bucket[key.charAt(0) - 'a']) {
            dp[0][offset] = Math.min(offset, ring.length() - offset) + 1;
        }

        for (int i = 1; i < key.length(); i++) {
            for (int j : bucket[key.charAt(i) - 'a']) {
                for (int k : bucket[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                            dp[i - 1][k] + Math.min(Math.abs(j - k), ring.length() - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[key.length() - 1]).filter(n->n != 0).min().getAsInt();
    }


    public static void main(String[] args) {
        FreedomTrailDp o = new FreedomTrailDp();

        int res = o.findRotateSteps("godding", "gd");

        System.out.println(res);
    }

}
