package aaMSsssssss;

/**
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 */
public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
