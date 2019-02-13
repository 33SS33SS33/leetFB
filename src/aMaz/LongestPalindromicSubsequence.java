package aMaz;

/**
 * Created by krystal on 7/3/17. TODO
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * Example 1:
 * Input: "bbbab" Output: 4 One possible longest palindromic subsequence is "bbbb".
 * Input: "cbbd" Output: 2 One possible longest palindromic subsequence is "bb".
 */

//https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        LongestPalindromicSubsequence p = new LongestPalindromicSubsequence();
        String k = "bbbab";
        String l = "abccccdd";
        System.out.println(p.longestPalindromicSubsequence(k));
        System.out.println(p.longestPalindromicSubsequence(l));
    }

    public int longestPalindromicSubsequence(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
