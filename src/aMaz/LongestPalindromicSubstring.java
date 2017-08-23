package aMaz;

/**
 * Given a string S, find the longest palindromic substring in S. You may
 * assume that the maximum length of S is 1000, and there exists one unique
 * longest palindromic substring.
 * Tags: String
 */
class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        String s = "qabbaee";
        System.out.println(l.longestPalindrome(s));
        System.out.println(l.longestPalindromeC(s));
        System.out.println(l.longestPalindromeD(s));
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
     * Insert char to the original input string If the index is even, return # If the index is odd,
     * return char in the original string
     */
    private char get(String s, int i) {
        if (i % 2 == 0)
            return '#';
        else
            return s.charAt(i / 2);
    }

    /**
     * O(n^2) Time, O(1) Space
     * Expand from center character and center of two chars
     * Update result according to the returned length
     * 基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，
     * 比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，直到不是回文串为止。
     * 假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
     * 对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)
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

    /**
     * O(n2) runtime, O(1) space – Simpler solution:
     */
    public String longestPalindromeC2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter2(s, i, i);
            int len2 = expandAroundCenter2(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter2(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 而第二种方法是用动态规划，思路比较复杂一些，但是实现代码会比较简短。
     * 基本思路是外层循环i从后往前扫，内层循环j从i当前字符扫到结尾处。
     * 过程中使用的历史信息是从i+1到n之间的任意子串是否是回文已经被记录下来，所以不用重新判断，
     * 只需要比较一下头尾字符即可。这种方法使用两层循环，时间复杂度是O(n^2)。
     * 而空间上因为需要记录任意子串是否为回文，需要O(n^2)的空间
     */
    public String longestPalindromeD(String s) {
        if (s == null || s.length() == 0)
            return "";
        boolean[][] palin = new boolean[s.length()][s.length()];
        String res = "";
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || palin[i + 1][j - 1])) {
                    palin[i][j] = true;
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }


}
