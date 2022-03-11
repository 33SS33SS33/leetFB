package aMaz;

class RegularExpressionMatching {
    public static void main(String[] args) {
        System.out.println(regularExpressionMatching("aa", "a"));
        System.out.println(regularExpressionMatching("aaa", "aa"));
        System.out.println(regularExpressionMatching("aa", "a*"));
        System.out.println(regularExpressionMatching("aa", ".*"));
        System.out.println(regularExpressionMatching("ab", ".*"));
        System.out.println(regularExpressionMatching("aab", "c*a*b"));
    }

    /**
     * Implement regular expression matching with support for '.' and '*'. HARD TODO
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     * The function prototype should be:
     * bool match(const char *s, const char *p)
     * Some examples:
     * match("aa","a") → false
     * match("aa","aa") → true
     * match("aaa","aa") → false
     * match("aa", "a*") → true
     * match("aa", ".*") → true
     * match("ab", ".*") → true
     * match("aab", "c*a*b") → true
     * Note: "*" only works on the preceding one element, not the whole string.
     * Tags: DP, Backtracking, String
     */
    public static boolean regularExpressionMatching(String s, String p) {
        if (s == null && p == null)
            return true;
        if (s == null || p == null)
            return false;
        if (p.length() == 0)
            return s.length() == 0;
        //p's length 1 is special case
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return regularExpressionMatching(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (regularExpressionMatching(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

}