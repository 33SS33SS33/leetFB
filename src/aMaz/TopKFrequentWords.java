package aMaz;

import java.util.*;

/**
 * Created by krystal on 1/23/19.
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = { "i", "love", "leetcode", "i", "love", "coding" };
        System.out.print(new TopKFrequentWords().topKFrequent(words, 2));
    }

    /**
     * The idea is to keep a count of each word in a HashMap and then insert in a Priority Queue.
     * While inserting in pq, if the count of two words is same then insert based on string compare of the keys.
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i]))
                map.put(words[i], map.get(words[i]) + 1);
            else
                map.put(words[i], 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Objects.equals(a.getValue(), b.getValue()) ?
                        b.getKey().compareTo(a.getKey()) :
                        a.getValue() - b.getValue());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k)
                pq.poll();
        }
        while (!pq.isEmpty())
            result.add(0, pq.poll().getKey());
        return result;
    }
}
