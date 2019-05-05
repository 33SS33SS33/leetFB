package aMaz;

import java.util.*;

/**
 * Given a string s, palindromePartition s such that every substring of the palindromePartition is a palindrome. HARD TODO
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Tags: DP
 * 先要用一个二维的dp表来记录下当前字符串 从i到j是不是一个回文
 * 然后再在建立表的同时  进行一维dp的判断 一维dp表示的是当前长度i的最小cut的数量
 * 本题十分重要 还有o1解法 未实现
 */
class PalindromePartitioning2 {
    public static void main(String[] args) {
        /*test minCut*/
        System.out.println(palindromePartition2("a"));
        System.out.println(palindromePartition2("aa"));
        System.out.println(palindromePartition2("aab"));
        System.out.println(palindromePartition2("aabbcc"));
        System.out.println(palindromePartition2("aabbccdd"));
        System.out.println(palindromePartition2("abcdcba"));
        System.out.println(palindromePartition2("abcd"));
    }

    /**
     * Backtracking, generate all cuts 最好的
     */
    public static int palindromePartition2(String s) {
        Set<String> palin = new HashSet<>();
        return minCut(s, 0, palin);
    }

    public static int minCut(String s, int count, Set<String> palin) {
        if (s == null || s.length() == 0 || isPalindrome(s)) {
            palin.add(s);
            return count;
        }
        int min = Integer.MAX_VALUE;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isPalindrome(s.substring(0, i))) {
                palin.add(s.substring(0, i));
                // add DP here
                int result = palin.contains(s.substring(i)) ? count : minCut(s.substring(i), count + 1, palin);
                min = Math.min(min, result);
            }
        }
        return min;
    }

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return false;
        if (s.length() == 1)
            return true;
        int i = 0;
        int len = s.length();
        while (i < len / 2) {
            if (s.charAt(i) != s.charAt(len - i - 1))
                return false;
            i++;
        }
        return true;
    }

}
