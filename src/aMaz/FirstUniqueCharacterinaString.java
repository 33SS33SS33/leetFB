package aMaz;

import java.util.HashMap;

public class FirstUniqueCharacterinaString {
    public static void main(String[] args) {
        System.out.println(firstUniqueCharacterinaString("eewd"));
        System.out.println(firstUniqueCharacterinaString("eewda"));
    }

    /**
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     * s = "leetcode" return 0.
     * s = "loveleetcode",return 2.
     * Note: You may assume the string contain only lowercase letters.
     * solution is pretty straightforward. It takes O(n) and goes through the string twice:
     * Get the frequency of each character.
     * Get the first character that has a frequency of one.
     */
    public static int firstUniqueCharacterinaString(String s) {
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public int firstUniqueCharacterinaStringb(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
