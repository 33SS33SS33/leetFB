package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2015/12/23.
 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
 * <p/>
 * 有很多解法 很重要的题目
 */
public class IsIsomorphic {
    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        System.out.println(new IsIsomorphic().isIsomorphica(s, t));
        System.out.println(new IsIsomorphic().isIsomorphicA(s, t));
        System.out.println(new IsIsomorphic().isIsomorphicB(s, t));
        System.out.println();
    }

    /**
     * The main idea is to store the last seen positions of current (i-th) characters in both 最好的
     * strings. If previously stored positions are different then we know that the fact they're
     * occuring in the current i-th position simultaneously is a mistake. We could use a
     * map for storing but as we deal with chars which are basically ints and can be used as
     * indices we can do the whole thing with an array
     */
    public boolean isIsomorphica(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i) + 256])
                return false;
            m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
        }
        return true;
    }

    public boolean isIsomorphicA(String s, String t) {
        if (s == null || t == null)
            return false;
        if (s.length() != t.length())
            return false;
        if (s.length() == 0 && t.length() == 0)
            return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            Character c = getKey(map, c2);
            if (c != null && c != c1) {
                return false;
            } else if (map.containsKey(c1)) {
                if (c2 != map.get(c1))
                    return false;
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    // a method for getting key of a target value
    public Character getKey(HashMap<Character, Character> map, Character target) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(target)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isIsomorphicB(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        if (S.length != T.length)
            return false;
        return isIsomorphic(S, T) && isIsomorphic(T, S);
    }

    boolean isIsomorphic(char[] S, char[] T) {
        char[] MAP = new char[256];
        for (int i = 0; i < S.length; i++) {
            if (MAP[(int) S[i]] == 0) {
                // not mapped
                MAP[(int) S[i]] = T[i];
            } else {
                if (MAP[(int) S[i]] != T[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}
