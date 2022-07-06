package aMaz;

import java.util.*;

public class WordBreak2 {
    public static void main(String[] args) {
        String s1 = "catsanddog";
        String s2 = "catsandog";
        Set<String> dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(wordBreakII(s1, dict).toString());
    }

    /**
     * TODO HARD
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
     * Return all such possible sentences.
     * Note:The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words
     * given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A solution is ["cats and dog", "cat sand dog"].
     * given s = "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"] Output:[]
     * Tags: DP, Backtracking
     * DFS会超时 可以加入word break I 里面的那个判断的dp 在每次dfs前先判断当前的s可以不可以被dic分掉
     */
    public static ArrayList<String> wordBreakII(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return res;
        helper(s, dict, 0, "", res);
        return res;
    }

    private static void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
        if (start >= s.length()) {
            res.add(item);
            return;
        }
        StringBuilder str = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            str.append(s.charAt(i));
            if (dict.contains(str.toString())) {
                String newItem = item.length() > 0 ? (item + " " + str) : str.toString();
                helper(s, dict, i + 1, newItem, res);
            }
        }
    }

    /**
     * Recursion with memoization
     * In the previous approach we can see that many subproblems were redundant, i.e we were calling the recursive function multiple times for the same substring appearing through multiple paths.
     * To avoid this we can use memorization method, where we are making use of a hashmap to store the results in the form of a key:value pair.
     * In this hashmap, the keykey used is the starting index of the string currently considered and the valuevalue contains all the sentences which can be formed using the substring from this starting index onwards.
     * Thus, if we encounter the same starting index from different function calls, we can return the result directly from the hashmap rather than going for redundant function calls.
     * With memorization many redundant subproblems are avoided and recursion tree is pruned and thus it reduces the time complexity by a large factor.
     * Time complexity : O(n^3) Size of recursion tree can go up to n^2  The creation of list takes n time.
     * Space complexity : O(n^3) The depth of the recursion tree can go up to nn and each activation record can contains a string list of size nn
     */
/*    public List<String> wordBreakA(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }

    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }*/

}
