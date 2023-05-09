package com.example.day15;

/**
 * @Date: 2023/5/8 19:31
 * 121. 买卖股票的最佳时机
 * <p>
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class Code03_maxProfit {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        //最大利润
        int max = 0;
        //最小买入价格
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            int now = Math.max(0, prices[i] - min); //今天卖股票的最大利润
            max = Math.max(max,now);
            min = Math.min(min,prices[i]);
        }
        return max;
    }
}
