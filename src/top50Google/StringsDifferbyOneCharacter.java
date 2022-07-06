package top50Google;

import java.util.*;

public class StringsDifferbyOneCharacter {
    /**
     * Given a list of strings dict where all the strings are of the same length.
     * Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.
     * Example 1:
     * Input: dict = ["abcd","acbd", "aacd"]
     * Output: true
     * Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
     */
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        int len = dict[0].length();
        for (int i = 0; i < len; i++) {
            set.clear();
            for (String str : dict) {
                String t = str.substring(0, i) + str.substring(i + 1, len);
                if (set.contains(t)) {
                    return true;
                }
                set.add(t);
            }
        }
        return false;
    }
}
