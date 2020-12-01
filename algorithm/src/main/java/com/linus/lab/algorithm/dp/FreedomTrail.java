package com.linus.lab.algorithm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 18:22
 * @Description TODO
 */
public class FreedomTrail {

    private String ring;
    private String key;

    List<Integer>[] bucket = new ArrayList[26];

    int[][] memo;

    public int findRotateSteps(String ring, String key) {
        this.ring = ring;
        this.key = key;
        memo = new int[key.length()][ring.length()];
        for (int i = 0; i < ring.length(); i++) {
            int index = ring.charAt(i) - 'a';
            if (bucket[index] == null) bucket[index] = new ArrayList<>();
            bucket[index].add(i);
        }
        return dfs(0, 0);
    }

    public int dfs(int ringIndex, int keyIndex) {
        if (keyIndex == key.length()) return 0;
        if (memo[keyIndex][ringIndex] > 0) return memo[keyIndex][ringIndex];

        char c = key.charAt(keyIndex);

        int minCost = Integer.MAX_VALUE;
        for (Integer index : bucket[c - 'a']) {
            int gap = Math.abs(ringIndex - index);
            gap = Math.min(gap, ring.length() - gap);
            int cost =  gap + 1 + dfs(index, keyIndex + 1);
            minCost = Math.min(cost, minCost);
        }
        memo[keyIndex][ringIndex] = minCost;
        return minCost;
    }


    public static void main(String[] args) {

    }
}
