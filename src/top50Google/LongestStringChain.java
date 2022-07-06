package top50Google;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {
    /**
     * You are given an array of words where each word consists of lowercase English letters.
     * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
     * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
     * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
     * Return the length of the longest possible word chain with words chosen from the given list of words.
     * <p>
     * Input: words = ["a","b","ba","bca","bda","bdca"]
     * Output: 4
     * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
     */

    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int longestWordSequenceLength = 1;
        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }
}
