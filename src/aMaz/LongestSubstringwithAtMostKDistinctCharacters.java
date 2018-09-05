package aMaz;

/**
 * HARD
 * Created by shanshan on 16/6/17.
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2, Output: 3  T is "ece" which its length is 3.
 * Input: s = "aa", k = 1 Output: 2  Explanation: T is "aa" which its length is 2.
 * 变形 返回string!!!~~~~
 */

public class LongestSubstringwithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));
    }

    //the slide window
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0)
                num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0) ;
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

}
