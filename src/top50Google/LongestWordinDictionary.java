package top50Google;

import java.util.*;

public class LongestWordinDictionary {
    /**
     * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character
     * at a time by other words in words.
     * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer,return the empty string.
     * Example 1:
     * Input: words = ["w","wo","wor","worl","world"]
     * Output: "world"
     * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
     */

    /**
     * Sort the words alphabetically, therefore shorter words always comes before longer words;
     * Along the sorted list, populate the words that can be built;
     * Any prefix of a word must comes before that word.
     */
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }
}
