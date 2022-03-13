package aMSsssssss;

/**
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 */
public class BestTimetoBuyandSellStock {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int price : prices) {
            if (price < minprice)
                minprice = price;
            else if (price - minprice > maxprofit)
                maxprofit = price - minprice;
        }
        return maxprofit;
    }
}
