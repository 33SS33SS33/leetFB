package aMaz;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by krystal on 7/3/17.
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        System.out.print(new SortCharactersByFrequency().sortCharactersByFrequency("tree"));
    }

    /**
     * Given a string, sort it in decreasing order based on the frequency of characters.
     * Input: "tree"  Output: "eert"
     * Explanation: 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     */
    public String sortCharactersByFrequency(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int) e.getValue(); i++)
                sb.append(e.getKey());
        }
        return sb.toString();
    }

}
