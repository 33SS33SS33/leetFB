package TopInterview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * Tags: DP
 * 一个DP问题。定义possible[i] 为S字符串上[0,i]的子串是否可以被segmented by dictionary.
 * 那么
 * possible[i] = true      if  S[0,i]在dictionary里面
 * = true      if   possible[k] == true 并且 S[k+1,j]在dictionary里面， 0<k<i
 * = false      if    no such k wordSearchb.
 * possible[0] = True 因为是空字符串
 * 大循环就是逐渐增加子串的长度  直到长度等于字符串
 * 小循环就是看看当前子串是否可能被segment
 */
class WordBreak {
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> words=Arrays.asList("leet", "code");
//        Set<String> dict = new HashSet<>(Arrays.asList(words));
        System.out.println(new WordBreak().wordBreak(s, words));
    }

    /**
     * DP, bottom-up  最好的
     * Build from first character to last in input
     * Record whether it can be break in a boolean array
     * Traverse from start to current position and check whether current
     * boolean is true and the rest in set
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];//???!!
        dp[0] = true;
        for (int i = 1; i <= len; i++) {//注意范围
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
