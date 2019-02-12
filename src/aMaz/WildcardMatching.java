package aMaz;

/**
 * Implement wildcard pattern matching with support for '?' and '*' '?' Matches any single character. HARD TODO
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool wildcardMatchingc(const char *s, const char *p)
 * Some examples:
 * wildcardMatchingc("aa","a") → false
 * wildcardMatchingc("aa","aa") → true
 * wildcardMatchingc("aaa","aa") → false
 * wildcardMatchingc("aa", "*") → true
 * wildcardMatchingc("aa", "a*") → true
 * wildcardMatchingc("ab", "?*") → true
 * wildcardMatchingc("aab", "c*a*b") → false
 * My own examples:
 * wildcardMatchingc("aab", "a*a*b") → true
 * wildcardMatchingc("a", "a*") → true
 * Tags: DP, Backtracking, Greedy, String
 */
class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching w = new WildcardMatching();
        System.out.println(w.wildcardMatchingb("a", "")); // false
        System.out.println(w.wildcardMatching("a", "")); // false
        System.out.println(w.wildcardMatchingb("", "")); // true
        System.out.println(w.wildcardMatchingb(null, null)); // true
        System.out.println(w.wildcardMatchingb(null, "null")); // false
        System.out.println(w.wildcardMatching(null, "null")); // false

        System.out.println(w.wildcardMatchingb("b", "*?*?")); // false
        System.out.println(w.wildcardMatching("b", "*?*?")); // false
    }

    public static boolean wildcardMatching(String s, String p) {
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
            return wildcardMatching(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (wildcardMatching(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

    /**
     * DP, two pointers O(length(s)+length(p)) 最好的
     * remember the index of * and matched sequence
     * advance only pattern pointer when * is found
     * match the sequence after * in pattern with the rest of the string
     */
    public boolean wildcardMatchingb(String s, String p) {
        if (s == null && p == null)
            return true;
        if (s == null || p == null)
            return false;
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
        while (i < s.length()) {
            // found ? or same chars ，advancing both pointers
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
                // * found, only advancing pattern pointer
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

    /**
     * O(m*n)
     */
    public boolean wildcardMatchingc(String s, String p) {
        if (s == null && p == null)
            return true;
        if (s == null || p == null)
            return false;
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

}
