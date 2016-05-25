package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/23.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
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
public class PalindromePairs {
    public static void main(String[] args) {
        String[] words= { "bat", "tab", "cat" };
        String[] words2= { "abcd", "dcba", "lls", "s", "sssll" };
        System.out.println(new PalindromePairs().palindromePairs(words));
        System.out.print(new PalindromePairs().palindromePairs(words2));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new LinkedList<List<Integer>>();
        if (words == null)
            return pairs;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; ++i)
            map.put(words[i], i);
        for (int i = 0; i < words.length; ++i) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if (j != null && i != j && isPalindrome( words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l)))
                    pairs.add(Arrays.asList(l == 0 ? new Integer[] { i, j } : new Integer[] { j, i }));
                if (r < words[i].length())
                    ++r;
                else
                    ++l;
            }
        }
        return pairs;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; ++i)
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        return true;
    }
}
