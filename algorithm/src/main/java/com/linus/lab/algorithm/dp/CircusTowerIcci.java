package com.linus.lab.algorithm.dp;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/12/2 11:13
 * @Description TODO
 * https://leetcode-cn.com/problems/circus-tower-lcci/
 *
 *
 *
 *5                        \
 *4 \                       \
 *3  \         \            *\*
 *2   \        *\*
 *1   *\*        \
 *0               \
 *
 * data = [{1,4},{1,3},{1,2},{1,1},{2,3},{2,2},{2,1},{2,0},{3,5},{3,4},{3,3}]
 * res = 3  weight:1->2->3
 *
 */
public class CircusTowerIcci {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int[][] pairs = new int[height.length][];
        for (int i = 0; i < height.length; i++) {
            pairs[i] = new int[]{height[i], weight[i]};
        }

        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] dp = new int[height.length];
        int res = 0;
        for (int[] pair : pairs) {
            int p = Arrays.binarySearch(dp, 0, res, pair[1]);
            if (p < 0) p = -p - 1;
            dp[p] = pair[1];
            if (res == p) res++;
        }
        return res;
    }

    public static void main(String[] args) {

        CircusTowerIcci o = new CircusTowerIcci();
        int res = o.bestSeqAtIndex(new int[]{1,2,2,3}, new int[]{1,2,2,1});
        System.out.println(res);

    }
}
