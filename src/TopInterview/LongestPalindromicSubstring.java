package TopInterview;

public class LongestPalindromicSubstring {
    /**
     * Given a string s, return the longest palindromic substring in s.
     * Input: s = "babad"
     * Output: "bab"
     * Explanation: "aba" is also a valid answer.
     */
    public String longestPalindromeC(String s) {
        if (s == null || s.length() == 0)
            return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            String s1 = expandAroundCenter(s, i, i);
            if (s1.length() > longest.length())
                longest = s1;
            String s2 = expandAroundCenter(s, i, i + 1);
            if (s2.length() > longest.length())
                longest = s2;
        }
        return longest;
    }

    private String expandAroundCenter(String s, int i, int j) {
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1
    }
}
