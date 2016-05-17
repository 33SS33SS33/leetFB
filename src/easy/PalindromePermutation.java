package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */
/**
 * "Given a string, determine if a permutation of the string could form a palindrome.
    For example,
 ""code"" -> False, ""aab"" -> True, ""carerac"" -> True."
 */

import java.util.HashMap;
import java.util.Map;

/**能不能回文就是最多只能有一个字母出现了奇数次	*/
public class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutation p = new PalindromePermutation();
        String k = "ewewi";
        String l = "code";
        String m = "carerac";
        System.out.println(p.canPermutePalindrome(k));
        System.out.println(p.canPermutePalindrome(l));
        System.out.println(p.canPermutePalindrome(m));
    }

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            Integer i = m.get(c);
            if (i == null) {
                m.put(c, 1);
            } else {
                m.put(c, i + 1);
            }
        }
        int single = 0;
        for (int v : m.values()) {
            if (v % 2 == 1) {
                single++;
            }
            if (single > 1) {
                return false;
            }
        }
        return true;
    }
}
