package aMaz;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i. HARD TODO
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * Input: [2,4,1], k = 2 Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Input: [3,2,6,5,0,3], k = 2 Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

class BestTimetoBuyandSellStockIV {
    public static void main(String[] args) {
        BestTimetoBuyandSellStockIV b = new BestTimetoBuyandSellStockIV();
        int[] A = new int[]{2,4,1};
        int[] B = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(b.bestTimetoBuyandSellStockIV(2, A));
        System.out.println(b.bestTimetoBuyandSellStockIV(2, B));
    }

    /**
     * You may complete at most k transactions.
     */
    public int bestTimetoBuyandSellStockIV(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k <= 0)
            return 0;
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

}
