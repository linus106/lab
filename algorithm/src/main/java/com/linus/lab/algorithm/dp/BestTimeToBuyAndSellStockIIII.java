package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/9
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIIII {


    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[k + 1][2];//i -> i th ; k -> k times; state-> hold or not hold stock


        for (int i = 1; i <= k; i++) {
            dp[i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }

    public int maxProfitCommon(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][][] dp = new int[prices.length][k + 1][2];//i -> i th ; k -> k times; state-> hold or not hold stock
        for (int i = 1; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }


    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIIII o = new BestTimeToBuyAndSellStockIIII();
        int res = o.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});
        System.out.println(res);
    }


}
