package easy;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * "Given a string, determine if a permutation of the string could form a palindrome.
 * For example,
 * ""code"" -> False, ""aab"" -> True, ""carerac"" -> True."
 * 能不能回文就是最多只能有一个字母出现了奇数次
 */
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

    public boolean canPermutePalindromea(String s) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); ++i) {
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else
                set.remove(s.charAt(i));
        }
        return set.size() == 0 || set.size() == 1;
    }

    public boolean canPermutePalindromea2(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes())
            bs.flip(b);
        return bs.cardinality() < 2;
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
