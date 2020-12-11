package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/9
 * @Description https://leetcode-cn.com/problems/unique-binary-search-trees
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j -1];
            }
        }
        return dp[n];
    }

    public int numTreesMath(int n) {//卡塔兰数
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    public static void main(String[] args) {

        UniqueBinarySearchTrees o = new UniqueBinarySearchTrees();
        int res = o.numTrees(3);
        System.out.println(res);

    }
}
