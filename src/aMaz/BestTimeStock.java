package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */
class BestTimeStock {
    public static void main(String[] args) {
        int[] prices = {1, 4, 2};
        System.out.println(bestTimeStock2(prices));
        System.out.println(bestTimeStock(prices));
    }

    /**
     * Say you have an array for which the ith element is the price of a given
     * stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one
     * and sell one share of the stock), design an algorithm to find the maximum
     * profit.
     * Tags: Array, DP
     */
    public static int bestTimeStock(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int local = 0;
        int global = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            local = Math.max(local + prices[i + 1] - prices[i], 0);
            global = Math.max(local, global);
        }
        return global;
    }

    /**
     * Optimized bottom-up approach  也很好啊
     * O(n) Time, O(1) Space
     * Just record yesterday's profit
     * Update min, max and profit
     * If next price is bigger, it's only possible to update the profit
     * If next price is smaller or equal, it's only possible to update min
     */
    public static int bestTimeStock2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0; // need at least 2
        int max = 0;
        int min = prices[0]; // track the minimum of profit array before cur ele
        for (int i = 1; i < prices.length; i++) { // note that i starts from 1
            min = Math.min(min, prices[i]); // update min
            if (prices[i] > prices[i - 1])
                max = Math.max(max, prices[i] - min);
        }
        return max;
    }

}
