package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28. HARD
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        String s = "mississippi";
        System.out.println(longestSubstringwithAtMostTwoDistinctCharacters(s));
    }

    //https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/discuss/49708/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
    // 双指针 最好的

    /**
     * Longest Substring with At Most Two Distinct Characters
     * Given a string s , find the length of the
     * longest substring t that contains at most 2 distinct characters.
     * Input: "eceba"  Output: 3 Explanation: t is "ece" which its length is 3.
     * Input: "ccaabbb" Output: 5 Explanation: t is "aabbb" which its length is 5.
     */
    public static int longestSubstringwithAtMostTwoDistinctCharacters(String s) {
        int[] count = new int[256];
        int i = 0, numDistinct = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)] == 0)
                numDistinct++;
            count[s.charAt(j)]++;
            while (numDistinct > 2) {
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0)
                    numDistinct--;
                i++;
            }
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }

}
