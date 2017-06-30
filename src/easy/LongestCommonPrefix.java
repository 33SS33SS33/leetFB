package easy;

/**
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * Tags: String
 */
class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"absss", "abddrre", "abs"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
        System.out.println(new LongestCommonPrefix().longestCommonPrefixA(strs));
        System.out.println(new LongestCommonPrefix().longestCommonPrefixB(strs));
        System.out.println(new LongestCommonPrefix().longestCommonPrefixC(strs));
    }

    /**
     * 最好的
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }

    /**
     * Find common prefix one by one from the end of the input string array
     * Overwrite the ith string with common prefix result
     * Thus space usage is reduced
     * Return first in group
     */
    public static String longestCommonPrefixA(String[] strs) {
        for (int i = strs.length - 2; i >= 0; i--) {
            strs[i] = commonPrefix(strs[i + 1], strs[i]);
        }
        return strs[0];
    }

    /**
     * Get length of two strings
     * Loop over each char till one length runs out
     * If same char, append it to result
     * If not same, break
     * Return result
     */
    private static String commonPrefix(String a, String b) {
        StringBuilder pref = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        int i = 0;
        while (i < lenA && i < lenB) {
            if (a.charAt(i) == b.charAt(i))
                pref.append(a.charAt(i));
            else
                break;
            i++;
        }
        return pref.toString();
    }

    /**
     * Only need to know the length of prefix
     * Initialize result with first word
     * Traverse from second word to last word
     * Get minimum length of current result and next word
     * Check whether prefix is that long in that length
     */
    public String longestCommonPrefixB(String[] strs) {
        if (strs == null)
            return null;
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        String word = strs[0];
        int prefixLength = word.length();
        for (int i = 1; i < strs.length; ++i) {
            String nextWord = strs[i];
            prefixLength = Math.min(prefixLength, nextWord.length());
            for (int j = 0; j < prefixLength; ++j)
                if (word.charAt(j) != nextWord.charAt(j)) {
                    prefixLength = j;
                    break;
                }
        }
        return word.substring(0, prefixLength);
    }

    public String longestCommonPrefixC(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            if (minLen > str.length())
                minLen = str.length();
        }
        if (minLen == 0)
            return "";
        for (int j = 0; j < minLen; j++) {
            char prev = '0';
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    prev = strs[i].charAt(j);
                    continue;
                }
                if (strs[i].charAt(j) != prev) {
                    return strs[i].substring(0, j);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }
}
