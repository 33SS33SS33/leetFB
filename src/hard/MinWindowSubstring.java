package hard;

import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * Tags: Hashtable, Two Pointers, String
 * 整体思路就是用两个指针 然后首先往前找到第一个window 然后就缩小start指针 然后再往前继续找
 * count是用来计算现在的窗口缺了几种T的字母 所以当一种都不缺的时候 就是找到了一个window 这时候就要开始进入while 移动start
 * 注意这里移动的时候 dic[s[start]]有可能是负数 就是这个字母出现的次数 多余T的次数  比如 AABC ABC 所以这种情况
 * start应该是移动到B才算停 因为这时候count才为1 表示缺少了一种字母
 * 但是移动的过程中每次都要计算一遍 end-start 这样才能的出来ABC
 * 还有别的解法 未实现
 */
class MinWindowSubstring {
    public static void main(String[] args) {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        String res = new MinWindowSubstring().minWindowB(S, T);
        String res2 = new MinWindowSubstring().minWindow(S, T);
        System.out.println(res);
        System.out.print(res2);
    }

    /**
     * creek----
     */
    public String minWindowB(String s, String t) {
        if (t.length() > s.length())
            return "";
        String result = "";
        HashMap<Character, Integer> target = new HashMap<Character, Integer>();//character counter for t
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            target.put(c, target.containsKey(c) ? target.get(c) + 1 : 1);
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();// character counter for s
        int left = 0;
        int minLen = s.length() + 1;
        int count = 0; // the total of mapped characters
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (target.containsKey(c)) {
                if (map.containsKey(c)) {
                    if (map.get(c) < target.get(c)) {
                        count++;
                    }
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                    count++;
                }
            }
            if (count == t.length()) {
                char sc = s.charAt(left);
                while (!map.containsKey(sc) || map.get(sc) > target.get(sc)) {
                    if (map.containsKey(sc) && map.get(sc) > target.get(sc))
                        map.put(sc, map.get(sc) - 1);
                    left++;
                    sc = s.charAt(left);
                }
                if (i - left + 1 < minLen) {
                    result = s.substring(left, i + 1);
                    minLen = i - left + 1;
                }
            }
        }
        return result;
    }

    /**
     * Use two maps to store count of characters
     * One for T and one for S
     * Use minLength to remember the minimum length of window
     * Use letterCnt to store whether S include all characters in T
     * Use two pointers, one slow, one fast
     * Traverse through S with fast pointer
     * If the character is in T, update it in window, update letterCnt
     * If letterCnt >= length of T, try to update slow pointer position
     * When we have chars that not in T or more than the count in T, update
     * Update count in window as well
     * Compare with minLength and update result as well
     */
    public String minWindow(String S, String T) {
        if (S == null || T == null)
            return "";
        if (S.length() == 0 || T.length() == 0 || T.length() > S.length())
            return "";
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            dict.put(c, dict.containsKey(c) ? dict.get(c) + 1 : 1);
        }
        HashMap<Character, Integer> found = new HashMap<Character, Integer>();
        int foundCounter = 0;
        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        String minWindow = "";
        while (end < S.length()) {
            char c = S.charAt(end);
            if (dict.containsKey(c)) {
                if (found.containsKey(c)) {
                    if (found.get(c) < dict.get(c))
                        foundCounter++;
                    found.put(c, found.get(c) + 1);
                } else {
                    found.put(c, 1);
                    foundCounter++;
                }
            }
            if (foundCounter == T.length()) {
                //When foundCounter equals to T.length(), in other words, found all characters.
                char sc = S.charAt(start);
                while (!found.containsKey(sc) || found.get(sc) > dict.get(sc)) {
                    if (found.containsKey(sc) && found.get(sc) > dict.get(sc))
                        found.put(sc, found.get(sc) - 1);
                    start++;
                    sc = S.charAt(start);
                }
                if (end - start + 1 < min) {
                    minWindow = S.substring(start, end + 1);
                    min = end - start + 1;
                }
            }
            end++;
        }
        return minWindow;
    }
}
