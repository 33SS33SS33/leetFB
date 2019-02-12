package aFB;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool wildcardMatchingc(const char *s, const char *p)
 * Some examples:
 * wildcardMatchingc("aa","a") → false
 * wildcardMatchingc("aa","aa") → true
 * wildcardMatchingc("aaa","aa") → false
 * wildcardMatchingc("aa", "a*") → true
 * wildcardMatchingc("aa", ".*") → true
 * wildcardMatchingc("ab", ".*") → true
 * wildcardMatchingc("aab", "c*a*b") → true
 * Note:
 * "*" only works on the preceding one element, not the whole string.
 * Tags: DP, Backtracking, String
 */
class RegularExpMatching {
    public static void main(String[] args) {
        System.out.println(isMatchB("aa", "a"));
        System.out.println(isMatchB("aaa", "aa"));
        System.out.println(isMatchB("aa", "a*"));
        System.out.println(isMatchB("aa", ".*"));
        System.out.println(isMatchB("ab", ".*"));
        System.out.println(isMatchB("aab", "c*a*b"));
    }

    /**
     * creek 最好的
     */
    public static boolean isMatchB(String s, String p) {
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
            return isMatchB(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatchB(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

}
