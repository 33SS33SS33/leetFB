package easy;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * <p/>
 * 用哈希表统计或者直接排序就行
 */
public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram r = new ValidAnagram();
        String a = "anagram";
        String b = "nagaram";
        System.out.println(r.isAnagrama(a, b));
        System.out.println(r.isAnagram(a, b));
    }

    /**
     * The idea is simple. It creates a size 26 int arrays as buckets for each letter in
     * alphabet. It increments the bucket value with String s and decrement with string t.
     * So if they are anagrams, all buckets should remain with initial value which is zero.
     * So just checking that and return
     */
    public boolean isAnagrama(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++)
            alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet)
            if (i != 0)
                return false;
        return true;
    }

    public boolean isAnagram(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        Arrays.sort(S);
        Arrays.sort(T);
        return Arrays.equals(S, T);
    }

}
