package top50Google;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StockPriceFluctuation {
    /**
     * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
     * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
     * Design an algorithm that:
     * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
     * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
     * Finds the maximum price the stock has been based on the current records.
     * Finds the minimum price the stock has been based on the current records.
     * Implement the StockPrice class:
     * StockPrice() Initializes the object with no price records.
     * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
     * int current() Returns the latest price of the stock.
     * int maximum() Returns the maximum price of the stock.
     * int minimum() Returns the minimum price of the stock.
     * Example 1:
     * Input
     * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
     * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
     * Output
     * [null, null, null, 5, 10, null, 5, null, 2]
     */

    private int latestTime;
    // Store price of each stock at each timestamp.
    private Map<Integer, Integer> timestampPriceMap;

    // Store stock prices in sorted order to get min and max price.
    private PriorityQueue<int[]> minHeap, maxHeap;

    public StockPriceFluctuation() {
        latestTime = 0;
        timestampPriceMap = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    }

    public void update(int timestamp, int price) {
        // Update latestTime to latest timestamp.
        latestTime = Math.max(latestTime, timestamp);

        // Add latest price for timestamp.
        timestampPriceMap.put(timestamp, price);

        minHeap.add(new int[]{price, timestamp});
        maxHeap.add(new int[]{price, timestamp});
    }

    public int current() {
        // Return latest price of the stock.
        return timestampPriceMap.get(latestTime);
    }

    public int maximum() {
        int[] top = maxHeap.peek();
        // Pop pairs from heap with the price doesn't match with hashmap.
        while (timestampPriceMap.get(top[1]) != top[0]) {
            maxHeap.remove();
            top = maxHeap.peek();
        }
        return top[0];
    }

    public int minimum() {
        int[] top = minHeap.peek();
        // Pop pairs from heap with the price doesn't match with hashmap.
        while (timestampPriceMap.get(top[1]) != top[0]) {
            minHeap.remove();
            top = minHeap.peek();
        }
        return top[0];
    }
}
