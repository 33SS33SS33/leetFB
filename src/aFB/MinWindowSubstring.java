package aFB;

import java.util.HashMap;

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
        String res = minWindowB(S, T);
        String res2 = minWindow2(S, T);
        System.out.println(res);
        System.out.println(res2);
    }

    //make the template more applicable for Longest Substring Without Repeating Character
    public int lengthOfLongestSubstringa(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int head = 0, i = 0;
        int[] sTable = new int[256];
        int repeat = 0;
        while (i < s.length()) {
            if (sTable[s.charAt(i++)]++ > 0) repeat++;   //total number of repeat
            while (repeat > 0) {
                if (sTable[s.charAt(head++)]-- > 1) repeat--;
            }
            len = Math.max(len, i - head);
        }
        return len;
    }

    public static String minWindow2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap();
        for (char c : s.toCharArray())
            map.put(c, 0);
        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.get(c1) > 0)
                counter--;
            map.put(c1, map.get(c1) - 1);
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if (map.get(c2) > 0)
                    counter++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    /**
     * creek----
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
    public static String minWindowB(String s, String t) {
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


}
