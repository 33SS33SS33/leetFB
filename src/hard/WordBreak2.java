package hard;

import java.util.*;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 * Tags: DP, Backtracking
 * DFS会超时 可以加入word break I 里面的那个判断的dp 在每次dfs前先判断当前的s可以不可以被dic分掉
 */
public class WordBreak2 {
    public static void main(String[] args) {
        String s = "catsanddog";
        Set<String> dict = new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(wordBreakC(s, dict).toString());
    }

    //最好的 20170727 流利英语说
    public static ArrayList<String> wordBreakC(String s, Set<String> dict) {
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
                String newItem = item.length() > 0 ? (item + " " + str.toString()) : str.toString();
                helper(s, dict, i + 1, newItem, res);
            }
        }
    }

}
