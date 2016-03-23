package string;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 */

/**
 * Longest Substring Without Repeating Characters
 */
public class LengthOfLongestSubstringWithoutRepeat {
    public static void main(String[] args) {
        String s = "mississippi";

        System.out.println(lengthOfLongestSubstring(s));
        System.out.print(lengthOfLongestSubstringB(s));
    }

    public static int lengthOfLongestSubstring(String s) {
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

    public static int lengthOfLongestSubstringB(String s) {
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
