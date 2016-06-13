package hard;

import java.util.HashMap;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 * Longest Substring with At Most Two Distinct Characters
 */

public class LongestSubstringWithTwoDistinct {
    public static void main(String[] args) {
        String s = "mississippi";
        System.out.println(lengthOfLongestSubstringTwoDistincta(s));
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
        System.out.print(lengthOfLongestSubstringTwoDistinctB(s));
    }

    /**
     * 最好的？
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1))
                continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1;
            }
            j = k - 1;
        }
        return Math.max(s.length() - i, maxLen);
    }

    public static int lengthOfLongestSubstringTwoDistinctB(String s) {
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

    public static int lengthOfLongestSubstringTwoDistincta(String s) {
        if (s.length() < 1)
            return 0;
        HashMap<Character, Integer> index = new HashMap<Character, Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while (hi < s.length()) {
            if (index.size() <= 2) {
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;
            }
            if (index.size() > 2) {
                int leftMost = s.length();
                for (int i : index.values()) {
                    leftMost = Math.min(leftMost, i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                lo = leftMost + 1;
            }
            maxLength = Math.max(maxLength, hi - lo);
        }
        return maxLength;
    }
}
