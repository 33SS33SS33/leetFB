package hard;

/**
 * Implement wildcard pattern matching with support for '?' and '*' '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * My own examples:
 * isMatch("aab", "a*a*b") → true
 * isMatch("a", "a*") → true
 * Tags: DP, Backtracking, Greedy, String
 */
class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching w = new WildcardMatching();
        /**input validation*/
        System.out.println(w.isMatch("", "a")); // false
        System.out.println(w.isMatchB("", "a")); // false

        System.out.println(w.isMatch("a", "")); // false
        System.out.println(w.isMatchB("a", "")); // false

        System.out.println(w.isMatch("", "")); // true
        System.out.println(w.isMatchB("", "")); // true

        System.out.println(w.isMatch(null, null)); // true
        System.out.println(w.isMatchB(null, null)); // true

        System.out.println(w.isMatch("a", null)); // false
        System.out.println(w.isMatchB("a", null)); // false
        System.out.println(w.isMatch(null, "null")); // false
        System.out.println(w.isMatchB(null, "null")); // false
        /**letters*/
        System.out.println(w.isMatch("a", "aa")); // false
        System.out.println(w.isMatch("aa", "a")); // false
        System.out.println(w.isMatch("aa", "aa")); // true
        System.out.println(w.isMatch("aa", "ab")); // false
        /**s*/
        System.out.println(w.isMatch("bbbbbbbbbb", "*bbbbb")); // true
        System.out.println(w.isMatchA("bbbbbbbbbb", "*bbbbb")); // true
        System.out.println(w.isMatchB("bbbbbbbbbb", "*bbbbb")); // true
        /** and ? */
        System.out.println(w.isMatch("b", "*?*?")); // false
        System.out.println(w.isMatchB("b", "*?*?")); // false
    }

    /**
     * DP, two pointers    O(length(s)+length(p))  最好的
     * remember the index of * and matched sequence
     * advance only pattern pointer when * is found
     * match the sequence after * in pattern with the rest of the string
     */
    public boolean isMatch(String str, String pattern) {
        if (str == null && pattern == null)
            return true;
        if (str == null || pattern == null)
            return false;
        int s = 0, p = 0, match = 0, astroIdx = -1; // must be -1
        while (s < str.length()) {
            // advancing both pointers
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) { // found ? or same chars
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') { // found *
                astroIdx = p; // save astroid index in pattern
                match = s; // save current index of string
                p++; // only move pattern pointer forward
            }
            // last pattern pointer was *, advancing string pointer
            else if (astroIdx != -1) { // try to find last astroid
                p = astroIdx + 1; // move to * one char behind astroid
                match++; // move current index of string
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else
                return false; // not ?, not same char, not *, don't match
        }
        // check remaining characters in pattern, can only be astroid
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        return p == pattern.length(); // no remaining
    }

    /**
     * O(m*n)
     */
    public boolean isMatchA(String s, String p) {
        int count = 0;
        for (char c : p.toCharArray()) {
            if (c == '*')
                count++;
        }
        if (p.length() - count > s.length())
            return false;
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            char pattern = p.charAt(j - 1);
            dp[j][0] = dp[j - 1][0] && pattern == '*';
            for (int i = 1; i <= s.length(); i++) {
                char letter = s.charAt(i - 1);
                if (pattern != '*') {
                    dp[j][i] = dp[j - 1][i - 1] && (pattern == '?' || pattern == letter);
                } else
                    dp[j][i] = dp[j][i - 1] || dp[j - 1][i];
            }
        }
        return dp[p.length()][s.length()];
    }

    /**
     * ---creek-----
     */
    public boolean isMatchB(String s, String p) {
        if (s == null && p == null)
            return true;
        if (s == null || p == null)
            return false;
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return j == p.length();
    }

}
