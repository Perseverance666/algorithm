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
    //自己写的
    public int maxProfit2(int[] prices) {
        int res = 0;
        int len = prices.length;
        if(prices == null || len <= 1){
            return res;
        }
        //dp[i]表示价格为prices[i]卖出时能获得的最大利润，dp[0]不用
        int[] dp = new int[len];
        //用于记录i前面的最小买入价格
        int min = prices[0];
        for(int i = 1; i < len; i++){
            dp[i] = prices[i] - min;
            res = Math.max(res,dp[i]);
            min = Math.min(min,prices[i]);
        }
        return res;
    }
}
