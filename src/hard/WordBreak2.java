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
 * <p>
 * DFS会超时 可以加入word break I 里面的那个判断的dp 在每次dfs前先判断当前的s可以不可以被dic分掉
 */
public class WordBreak2 {
    public static void main(String[] args) {
        String s = "catsanddog";
        Set<String> dict = new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> res = new WordBreak2().wordBreak(s, dict);
        System.out.println(res.toString());
        List<String> res2 = new WordBreak2().wordBreakB(s, dict);
        System.out.println(res2.toString());
        System.out.println(new WordBreak2().wordBreakC(s, dict).toString());
    }

    //最好的
    public ArrayList<String> wordBreakC(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return res;
        helper(s, dict, 0, "", res);
        return res;
    }

    private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
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

    /**
     * Memory function
     * Store how a word can be decomposed
     */

    /**
     * DP, Backtracking
     * Store successful decomposition in a map
     * Get prefix
     * If not in dictionary, just ignore
     * If in dictionary, check current position
     * If reaches the end, add prefix to a solution
     * If within length do the following:
     * Check whether the rest of the string is already decomposed
     * If not, backtracking the rest of the string
     * If yes, get the result from memory function
     * If there is an result, add each word to current solution with front in
     */
    Map<String, List<String>> res = new HashMap<String, List<String>>();

    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String pref = s.substring(0, i);
            if (dict.contains(pref)) {
                if (i == len)
                    words.add(pref); // reach the end
                else {
                    String remain = s.substring(i, len); // remaining string
                    // avoid backtracking if a decomposition is already there
                    List<String> remainDecomp = res.containsKey(remain) ? res.get(remain) : wordBreak(remain, dict);
                    if (remainDecomp != null) {
                        for (String w : remainDecomp)
                            words.add(pref + " " + w);
                        res.put(remain, remainDecomp); // add to cache
                    }
                }
            }
        }
        return words;
    }

    /**
     * Backtracking
     * Get prefix first
     * If prefix is in dictionary, check current length
     * If reaches the end, add prefix to result
     * If not, go ahead and decompose the remain string
     * Get the result list, and concat prefix with those results
     * Add the concatenated string to result and return
     */
    public List<String> wordBreakB(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String pref = s.substring(0, i);
            if (dict.contains(pref)) {
                if (i == len)
                    words.add(pref);
                else {
                    String remain = s.substring(i, len);
                    List<String> remainDecomp = wordBreakB(remain, dict);
                    if (remainDecomp != null) { // has decompositions
                        for (String item : remainDecomp) {
                            words.add(pref + " " + item);
                        }
                    }
                }
            }
        }
        return words;
    }

}
