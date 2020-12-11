package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/9
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {


    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][][] dp = new int[prices.length][3][2];//i -> i th ; k -> k times; state-> hold or not hold stock

        dp[0][0][0] = 0;
        dp[0][1][0] = 0;
        dp[0][2][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
//            dp[i][0][0] = 0;//没必要赋值
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
//            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][0][0] - prices[i]);//不可能出现
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);

        }
        return dp[prices.length - 1][2][0];
    }

    public int maxProfitOpt(int[] prices) {
        if (prices.length == 0) return 0;
        int[][][] dp = new int[prices.length][3][2];//i -> i th ; k -> k times; state-> hold or not hold stock

        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
//            dp[i][0][0] = 0;//没必要赋值
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
//            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][0][0] - prices[i]);//不可能出现
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);

        }
        return dp[prices.length - 1][2][0];
    }

    public int maxProfitAbstract(int[] prices) {
        return new BestTimeToBuyAndSellStockIIII().maxProfit(2, prices);
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII o = new BestTimeToBuyAndSellStockIII();
        int res = o.maxProfitAbstract(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println(res);
    }



}
