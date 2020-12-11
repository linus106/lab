package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/9
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {


    /**
     * how much will we earn when sell on ith day
     */
    public int maxProfitOpt(int[] prices) {
        if (prices.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            if (price < min) {
                min = Math.min(min, price);
            } else {
                res = Math.max(res, price - min);
            }
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int[] min = new int[prices.length];
        min[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
        }
        int[] max = new int[prices.length];
        max[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i+1], prices[i]);
        }
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, max[i] - min[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock o = new BestTimeToBuyAndSellStock();
        int res = o.maxProfitOpt(new int[]{7,1,5,3,6,4});
        System.out.println(res);

    }
}
