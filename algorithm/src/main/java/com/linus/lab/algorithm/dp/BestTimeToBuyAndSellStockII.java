package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/9
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {


    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int gap = prices[i] - prices[i - 1];
            if (gap > 0) res+=gap;
        }
        return res;
    }

    public int maxProfitCommon(int[] prices) {
        int hold = -prices[0], sold = 0;
        for (int i = 1; i < prices.length; i++) {
            sold = Math.max(sold, hold + prices[i]);
            hold = Math.max(hold, sold - prices[i]);
        }
        return sold;
    }


}
