package dP;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
 * sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day) Example:
 * prices = [1, 2, 3, 0, 2] maxProfit = 3 transactions = [buy, sell, cooldown, buy, sell]
 * Tags: Dynamic Programming
 * Similar Problems: (M) Best Time to Buy and Sell Stock, (M) Best Time to Buy and Sell Stock II
 * "这题的思路看右边的链接就行 十分清楚
 * 十分重要!! 以后dp的题都试着画出来状态的图
 * * s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
 * s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
 * s2[i] = s1[i - 1] + prices[i]; // Only one way from s1"
 */
public class BestTimeToBuySellStockWithCooldown {
    public static void main(String[] args) {
        int[] prices = {1, 2, 10, 100};
        System.out.println(maxProfita(prices));
        System.out.println(maxProfit(prices));
    }

    public static int maxProfita(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    /**
     * https://leetcode.com/discuss/71354/share-my-thinking-process
     * O(n) Time, O(1) Space
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int sell = 0, lastSell = 0, buy = -prices[0], lastBuy;
        for (int price : prices) {
            lastBuy = buy;
            buy = Math.max(lastSell - price, lastBuy);
            lastSell = sell;
            sell = Math.max(lastBuy + price, lastSell);
        }
        return sell;
    }

}