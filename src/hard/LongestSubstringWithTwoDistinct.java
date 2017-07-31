package hard;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 * Longest Substring with At Most Two Distinct Characters
 */

public class LongestSubstringWithTwoDistinct {
    public static void main(String[] args) {
        String s = "mississippi";
        System.out.println(lengthOfLongestSubstringTwoDistinctB(s));
    }

    // 双指针 最好的
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

}
