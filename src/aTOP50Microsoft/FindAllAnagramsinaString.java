package aTOP50Microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsinaString {
    public static void main(String[] args) {
        System.out.println(findAllAnagramsinaString("cbaebabacd", "abc"));
        System.out.println(findAllAnagramsinaString("abab", "ab"));
    }

    public static List<Integer> findAllAnagramsinaString(String s, String p) {
        char[] pt = p.toCharArray();
        Arrays.sort(pt);
        String pp = new String(pt);
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return list;
        //注意范围
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            String sub = s.substring(i, i + p.length());
            char[] t = sub.toCharArray();
            Arrays.sort(t);
            if (pp.equals(new String(t))) {
                list.add(i);
            }
        }
        return list;
    }
}
