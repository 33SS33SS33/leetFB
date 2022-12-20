package aMaz;

public class ImplementStrStr {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issi"));
        System.out.println(strStr("hello", "ll"));
    }

    /**
     * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * 两个循环 大循环就是遍历长字符串 然后在长字符串的每个起始位都开始小循环比对字符
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Tags: Two Pointers, String
     */
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0)
            return 0;
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length())
                    return i;
                if (i + j == haystack.length())
                    return -1;
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
            }
        }
    }
}
