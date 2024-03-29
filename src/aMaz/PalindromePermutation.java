package aMaz;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutation p = new PalindromePermutation();
        String k = "ewewi";
        String l = "code";
        String m = "carerac";
        System.out.println(p.palindromePermutation(k));
    }

    /**
     * "Given a string, determine if a permutation of the string could form a palindrome.
     * ""code"" -> False, ""aab"" -> True, ""carerac"" -> True."
     * 能不能回文就是最多只能有一个字母出现了奇数次
     */
    public boolean palindromePermutation(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else
                set.remove(s.charAt(i));
        }
        return set.size() == 0 || set.size() == 1;
    }
}
