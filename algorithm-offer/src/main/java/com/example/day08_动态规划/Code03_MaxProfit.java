package com.example.day08_动态规划;

/**
 * @Date: 2023/3/10 20:19
 * 剑指 Offer 63. 股票的最大利润
 */
public class Code03_MaxProfit {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for(int i = 0; i < prices.length; i++){
            cost = Math.min(cost,prices[i]);
            profit = Math.max(profit,prices[i] - cost);
        }
        return profit;
    }
}
