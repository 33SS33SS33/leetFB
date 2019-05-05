package aMaz;

/**
 * Created by shanshan on 1/29/19.
 */
public class PalindromicSubstrings {
    /**
     * Given a string, your task is to count how many palindromic substrings in this string.
     * The substrings with different start indexes or end indexes are
     * counted as different substrings even they consist of same characters.
     * Input: "abc" Output: 3  Explanation: Three palindromic strings: "a", "b", "c".
     * Input: "aaa" Output: 6  Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     * Note: The input string length won't exceed 1000.
     */
    int count = 1;

    public int palindromicSubstrings(String s) {
        if (s.length() == 0)
            return 0;
        for (int i = 0; i < s.length() - 1; i++) {
            checkPalindrome(s, i, i); //To check the palindrome of odd length palindromic sub-string
            checkPalindrome(s, i, i + 1);//To check the palindrome of even length palindromic sub-string
        }
        return count;
    }

    private void checkPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {//Check for the palindrome string
            count++;    //Increment the count if palindromin substring found
            i--;    //To trace string in left direction
            j++;    //To trace string in right direction
        }
    }
}
