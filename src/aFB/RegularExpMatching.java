package aFB;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * Note:
 * "*" only works on the preceding one element, not the whole string.
 * Tags: DP, Backtracking, String
 */
class RegularExpMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatchB("aa", "a"));
        System.out.println(isMatchC("aa", "a"));

        System.out.println(isMatch("aa", "aa"));
        System.out.println(isMatchB("aa", "aa"));
        System.out.println(isMatchC("aa", "aa"));

        System.out.println(isMatch("aaa", "aa"));
        System.out.println(isMatchB("aaa", "aa"));
        System.out.println(isMatchC("aaa", "aa"));

        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatchB("aa", "a*"));
        System.out.println(isMatchC("aa", "a*"));

        System.out.println(isMatch("aa", ".*"));
        System.out.println(isMatchB("aa", ".*"));
        System.out.println(isMatchC("aa", ".*"));

        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatchB("ab", ".*"));
        System.out.println(isMatchC("ab", ".*"));

        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatchB("aab", "c*a*b"));
        System.out.println(isMatchC("aab", "c*a*b"));
    }

    /**
     * creek 最好的
     */
    public static boolean isMatchB(String s, String p) {
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

    /**
     * Basically, the OPT[i][j] means preceding substring of length i of s and
     * length j of p. For any two substrings, the value of OPT[i][j] can be
     * from one of following four cases:
     * case 1: OPT[i-1][j-1] is true, and ith character of s is equal to j th
     * character of p. Or j th character of p is '.', match
     * case 2: OPT[i-1][j] is true, then my pattern now is '*' and preceding
     * character is equal to incoming character of s
     * case 3: OPT[i][j-1] is true, then my pattern now is '*' which can match
     * an empty string
     * case 4: OPT[i][j-2] is true, and the pattern like (a*) matches an empty
     * string,
     * base case is the OPT[0][0], OPT[i][0], OPT[0][j].
     */
    public static boolean isMatch(String s, String p) {
        /*validation*/
        if (s == null || p == null)
            return s == p;
        if (s.length() == 0 && p.length() == 0)
            return true;
        boolean[][] mat = new boolean[s.length() + 1][p.length() + 1];
        mat[0][0] = true; // base case
        // fill first column p.length() == 0
        for (int i = 1; i <= s.length(); i++)
            mat[i][0] = false;
        // fill first row
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') { // j - 1 is current index in pattern
                mat[0][j] = mat[0][j - 2]; // same as two p chars before
            } else {
                mat[0][j] = false;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1)
                        || p.charAt(j - 1) == '.') { // not "*", matches
                    mat[i][j] = mat[i - 1][j - 1]; // same as last match
                } else if (p.charAt(j - 1) == '*') { // "*"
                    if (p.charAt(j - 2) == s.charAt(i - 1)
                            || p.charAt(j - 2) == '.') { // chars before "*" matches
                        mat[i][j] = mat[i - 1][j] || mat[i][j - 2] || mat[i][j
                                - 1]; // "a  " or "a*" or "*"
                    } else { // char before "*" don't match
                        mat[i][j] = mat[i][j - 2]; // same as two p chars before
                    }
                } else { // simply don't match
                    mat[i][j] = false;
                }
            }
        }
        return mat[s.length()][p.length()];
    }

    /**
     * creek2
     */
    public static boolean isMatchC(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() == 1) {   // special case
            // if the length of s is 0, return false
            if (s.length() < 1) {
                return false;
            }
            //if the first does not match, return false
            else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            }
            // otherwise, compare the rest of the string of s and p.
            else {
                return isMatchC(s.substring(1), p.substring(1));
            }
        }
        // case 1: when the second char of p is not '*'
        if (p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            }
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else {
                return isMatchC(s.substring(1), p.substring(1));
            }
        }
        // case 2: when the second char of p is '*', complex case.
        else {
            //case 2.1: a char & '*' can stand for 0 element
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            //case 2.2: a char & '*' can stand for 1 or more preceding element,
            //so try every sub string
            int i = 0;
            while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
}
