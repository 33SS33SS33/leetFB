package easy;

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
        System.out.println(lengthOfLongestSubstringA("abcabcbb"));
        System.out.println(lengthOfLongestSubstringB("abcabcbb"));
        System.out.println(lengthOfLongestSubstringC("abcabcbb"));
        System.out.println(lengthOfLongestSubstringD("abcabcbb"));
        System.out.println(lengthOfLongestSubstringB("bbbbb"));
        System.out.println(lengthOfLongestSubstringC("bbbbb"));
        System.out.println(lengthOfLongestSubstringC(""));
        System.out.println(lengthOfLongestSubstringC("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringB("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringA("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringD("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringe("fdjskajfhh"));
        System.out.println(lengthOfLongestSubstringBf("fdjskajfhh"));
    }

    /**
     * 最好的
     * Traverse the string
     * Get current character
     * Update start point
     * Update max
     * Put current char in map
     * he basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values,
     * and keep two pointers which define the max substring. move the right pointer to scan through the string , and meanwhile update the hashmap.
     * If the character is already in the hashmap,
     * then move the left pointer to the right of the same character last found. Note that the two pointers can only move forward.
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

    public static int lengthOfLongestSubstringA(String s) {
        if (s == null)
            return 0;
        boolean[] flag = new boolean[256];
        int result = 0;
        int start = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (flag[current]) {
                result = Math.max(result, i - start);
                // the loop update the new start point
                // and reset flag array
                // for example, abccab, when it comes to 2nd c,
                // it update start from 0 to 3, reset flag for a,b
                for (int k = start; k < i; k++) {
                    if (arr[k] == current) {
                        start = k + 1;
                        break;
                    }
                    flag[arr[k]] = false;
                }
            } else {
                flag[current] = true;
            }
        }
        result = Math.max(arr.length - start, result);
        return result;
    }

    public static int lengthOfLongestSubstringD(String s) {
        if (s == null)
            return 0;
        char[] arr = s.toCharArray();
        int pre = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            } else {
                pre = Math.max(pre, map.size());
                i = map.get(arr[i]);
                map.clear();
            }
        }
        return Math.max(pre, map.size());
    }

    public static int lengthOfLongestSubstringC(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (s == null)
            return 0;
        char[] str = s.toCharArray();
        if (str.length == 0)
            return 0;
        int max = 1;
        int barrier = 0;
        for (int i = 1; i < str.length; i++) {
            for (int j = i - 1; j >= barrier; j--) {
                if (str[i] == str[j]) {
                    barrier = j + 1;
                    break;
                }
            }
            max = Math.max(max, i - barrier + 1);
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

    public static int lengthOfLongestSubstringBf(String s) {
        int[] charMap = new int[256];
        Arrays.fill(charMap, -1);
        int i = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            if (charMap[s.charAt(j)] >= i) {
                i = charMap[s.charAt(j)] + 1;
            }
            charMap[s.charAt(j)] = j;
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }
}
