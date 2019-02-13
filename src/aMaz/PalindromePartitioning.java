package aMaz;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, palindromePartition s such that every substring of the palindromePartition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * Tags: Backtracking
 * 用的DFS
 * 把当前的字符串从头开始切割 如果切出来的是回文 就把剩下的字符串继续切 然后继续判断 直到切完了整个字符串
 * 回文的特点是正反都一样 s == s[::-1] 后面的切片就是把字符串逆序输出
 * 还要注意切片的循环的范围选择for i in x range(1,len(s)): 这里用的是从1到len(s) 这样可以保证不会切出来空的list
 * 但是也会导致并没有对s整体进行判断(因为最大切到倒数第二位)
 * 所以在下面又加了if对整体的s以及长度等于1的s进行判断
 */
class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(palindromePartition("aab"));
    }

    /**
     * Backtracking
     */
    public static List<List<String>> palindromePartition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        partition(s, 0, res, new ArrayList<>());
        return res;
    }

    public static void partition(String s, int pos, List<List<String>> res, List<String> cut) {
        if (pos == s.length()) { // note the stop condition
            res.add(new ArrayList<>(cut)); // dereference
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {// start from 1??
            String prefix = s.substring(pos, i);
            if (isPalindrome(prefix)) {
                cut.add(prefix);
                partition(s, i, res, cut); // update pos with i
                cut.remove(cut.size() - 1);
            }
        }

    }

    private static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            if (str.charAt(s) != str.charAt(e))
                return false;
            s++;
            e--;
        }
        return true;
    }

}
