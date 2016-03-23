package medium;

/**
 * Given a string S, find the longest palindromic substring in S. You may
 * assume that the maximum length of S is 1000, and there exists one unique
 * longest palindromic substring.
 * <p/>
 * Tags: String
 */
class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        String s = "qabbaee";
        System.out.println(l.longestPalindrome(s));
        System.out.println(l.longestPalindromeB(s));
        System.out.println(l.longestPalindromeC(s));
        System.out.println(l.longestPalindrome2(s));
    }

    /**
     * Manacher's Algorithm, O(n) Time.
     * S = “abba” => T = “#a#b#b#a#”.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        int len = s.length();
        int max = 0; // max length
        String res = "";
        for (int i = 1; i <= 2 * len - 1; i++) { // skip two #s
            int count = 1;
            while (i - count >= 0 && i + count <= 2 * len && get(s, i - count) == get(s, i + count))
                count++;
            count--; // there will be one extra count for the outbound #
            if (count > max) { // update max and result when longer is found
                res = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }
        return res;
    }

    /**
     * Insert char to the original input string
     * If the index is even, return #
     * If the index is odd, return char in the original string
     */
    private char get(String s, int i) {
        if (i % 2 == 0)
            return '#';
        else
            return s.charAt(i / 2);
    }

    /**
     * Manacher's Algorithm, O(n) Time.
     * S = “abba” => T = “^#a#b#b#a#$”.
     * http://www.felix021.com/blog/read.php?2040
     * http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
     */
    public String longestPalindromeB(String s) {
        String t = preProcess(s);
        int n = t.length();
        int[] p = new int[n];
        int range = 0, center = 0;
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i; // mirror of i to center
            p[i] = range > i ? Math.min(range - i, p[mirror]) : 0;
            while (t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i]))
                p[i]++;
            if (i + p[i] > range) {
                center = i;
                range = i + p[i];
            }
        }
        int maxLen = 0;
        int centerIdx = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIdx = i;
            }
        }
        return s.substring((centerIdx - 1 - maxLen) / 2, (centerIdx - 1 + maxLen) / 2);
    }

    private String preProcess(String s) {
        int n = s.length();
        if (n == 0)
            return "^$";
        String res = "^";
        for (int i = 0; i < n; i++) {
            res += "#" + s.substring(i, i + 1);
        }
        res += "#$";
        return res;
    }

    /**
     * DP Time O(n^2) Space O(n^2)
     */
    public static String longestPalindrome2(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s;
        int maxLen = 0;
        String longestStr = null;
        int length = s.length();
        int[][] table = new int[length][length];
        //every single letter is palindrome
        for (int i = 0; i < length; i++) {
            table[i][i] = 1;
        }
//        printTable(table);
        //e.g. bcba
        //two consecutive same letters are palindrome
        for (int i = 0; i <= length - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = 1;
                longestStr = s.substring(i, i + 2);
            }
        }
//        printTable(table);
        //condition for calculate whole table
        for (int l = 3; l <= length; l++) {
            for (int i = 0; i <= length - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] == 1 && l > maxLen)
                        longestStr = s.substring(i, j + 1);
                } else {
                    table[i][j] = 0;
                }
//                printTable(table);
            }
        }
        return longestStr;
    }

    public static void printTable(int[][] x) {
        for (int[] y : x) {
            for (int z : y) {
                System.out.print(z + " ");
            }
            System.out.println();
        }
        System.out.println("------");
    }

    /**
     * O(n^2) Time, O(1) Space
     * Expand from center character and center of two chars
     * Update result according to the returned length
     */
    public String longestPalindromeC(String s) {
        if (s == null || s.length() == 0)
            return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            String s1 = expandAroundCenter(s, i, i);
            if (s1.length() > longest.length())
                longest = s1;
            String s2 = expandAroundCenter(s, i, i + 1);
            if (s2.length() > longest.length())
                longest = s2;
        }
        return longest;
    }

    /**
     * Search for range in both direction
     */
    private String expandAroundCenter(String s, int i, int j) {
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1
    }
}
