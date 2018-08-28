package hard;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 */

class BestTimeStock4 {
    public static void main(String[] args) {
        BestTimeStock4 b = new BestTimeStock4();
        int[] A = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] B = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(b.maxProfitA(2, A));
        System.out.println(b.maxProfitA(2, B));
    }

    /**
     * You may complete at most k transactions.
     */
    public int maxProfitA(int k, int[] prices) {
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
