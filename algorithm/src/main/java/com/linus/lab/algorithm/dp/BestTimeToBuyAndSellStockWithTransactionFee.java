package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/7
 * @Description TODO
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];

        // assume dp as the money ith day wo hold;
        // and assume we deduct fee when buy stock;
        // nothold stock
        // hold stock
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i] - fee, dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {

    }
}
