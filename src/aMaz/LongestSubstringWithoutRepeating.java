package aMaz;

import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest
 * substring is "b", with the length of 1.
 * Tags: Hashtable, Two pointers, String
 */
class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstringB("abcabcbb"));
        System.out.println(lengthOfLongestSubstringB("bbbbb"));
        System.out.println(lengthOfLongestSubstringB("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringe("fdjskajfhh"));
    }

    /**
     * 最好的
     * Traverse the string
     * Get current character
     * Update start point
     * Update max
     * Put current char in map
     * he basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values,
     * and keep two pointers which define the max substring. move the right pointer to scan through the string ,
     * and meanwhile update the hashmap.
     * If the character is already in the hashmap,
     * then move the left pointer to the right of the same character last found.
     * Note that the two pointers can only move forward.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static int lengthOfLongestSubstringB(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            /*start point can be recent dup's next char or last start*/
            start = Math.max(start, (map.containsKey(c)) ? map.get(c) + 1 : 0);
            /*if current str len is bigger then update*/
            max = Math.max(max, i - start + 1);
            /*add char to map with index*/
            map.put(c, i);
        }
        return max;
    }

    public static int lengthOfLongestSubstringe(String s) {
        boolean[] exist = new boolean[256];
        int i = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            while (exist[s.charAt(j)]) {
                exist[s.charAt(i)] = false;
                i++;
            }
            exist[s.charAt(j)] = true;
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }

}
