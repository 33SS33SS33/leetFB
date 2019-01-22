package aMaz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by shanshan on 8/27/18.
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * <p>
 * Example:
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 */
public class MostCommonWord {
    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[] { "hit" };
        System.out.println(new MostCommonWord().mostCommonWord(paragraph, banned));
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String b : banned)
            set.add(b);
        Map<String, Integer> map = new HashMap<>();
        String ans = "";
        int count = 0;
        for (String s : paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ")) {
            s = s.trim();
            if (s.length() == 0 || set.contains(s))
                continue;
            map.put(s, map.getOrDefault(s, 0) + 1);
            if (count < map.get(s)) {
                count = map.get(s);
                ans = s;
            }
        }
        return ans;
    }
}
