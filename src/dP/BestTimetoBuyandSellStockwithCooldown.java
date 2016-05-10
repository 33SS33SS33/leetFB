package dP;

/**
 * Created by shanshan on 16/5/10.
 */

/**"这题的思路看右边的链接就行 十分清楚
 十分重要!! 以后dp的题都试着画出来状态的图

 s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
 s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
 s2[i] = s1[i - 1] + prices[i]; // Only one way from s1"	"Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)"*/
public class BestTimetoBuyandSellStockwithCooldown {
}
