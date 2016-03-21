package medium;

/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most 
 * <strong>two</strong> transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Tags: Array, DP
 */

/**Prices: 1 4 5 7 6 3 2 9
 left = [0, 3, 4, 6, 6, 6, 6, 8]
 right= [8, 7, 7, 7, 7, 7, 7, 0]
 The maximum profit = 13*/
class BestTimeStock3 {
    public static void main(String[] args) {
        BestTimeStock3 b = new BestTimeStock3();
        int[] prices = { 6, 1, 3, 2, 4, 7, 6, 10, 15 };
        System.out.println(b.maxProfit(prices));
        System.out.println(b.maxProfitA(prices));
    }

    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length < 2) return maxProfit;
        int len = prices.length;
        int[] maxBy = new int[len];
        int[] maxSince = new int[len];
        int valley = prices[0];
        int peak = prices[len - 1];

        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
            maxBy[i] = Math.max(maxBy[i - 1], prices[i] - valley);
        }
        /*update maxProfit while build maxSince*/
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            maxSince[i] = Math.max(maxSince[i + 1], peak - prices[i]);
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]); // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
        }
        return maxProfit;
    }

    /**creek*/
    public int maxProfitA(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }
}
