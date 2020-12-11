package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/7
 * @Description TODO
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int hold = -prices[0], cooldown = 0, waitting = 0;
        for (int i = 1; i < prices.length; i++) {
            int oldCoolDonw = cooldown;
            cooldown = waitting;
            waitting = Math.max(waitting, hold + prices[i]);
            hold = Math.max(hold, oldCoolDonw - prices[i]);
        }
        return Math.max(cooldown, waitting);
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCoolDown o = new BestTimeToBuyAndSellStockWithCoolDown();
        int res = o.maxProfit(new int[]{1, 2, 3, 0, 2});
        System.out.println(res);
    }
}
