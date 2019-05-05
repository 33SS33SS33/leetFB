package aMaz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/5/23. HARD TODO
 */
public class PalindromePairs {
    public static void main(String[] args) {
        String[] words = {"bat", "tab", "cat"};
        String[] words2 = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(palindromePairs(words));
        System.out.println(palindromePairs(words2));
    }

    /**
     * HARD Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
     * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     * Example 1:
     * Given words = ["bat", "tab", "cat"]
     * Return [[0, 1], [1, 0]]
     * The palindromes are ["battab", "tabbat"]
     * Example 2:
     * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
     * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
     * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new LinkedList<>();
        if (words == null)
            return pairs;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i)
            map.put(words[i], i);
        for (int i = 0; i < words.length; ++i) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if (j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l)))
                    pairs.add(Arrays.asList(l == 0 ? new Integer[]{i, j} : new Integer[]{j, i}));
                if (r < words[i].length())
                    ++r;
                else
                    ++l;
            }
        }
        return pairs;
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }

}
