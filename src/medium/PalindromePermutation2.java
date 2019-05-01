package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PalindromePermutation2 {
    public static void main(String[] args) {
        String k = "ewewi";
        String l = "code";
        String m = "carerac";
        System.out.println(generatePalindromes(k));
    }

    /**
     * "Given a string s, return all the palindromic permutations (without duplicates) of it.
     * Return an empty list if no palindromic permutation could be form.
     * For example:
     * Given s = ""aabb"", return [""abba"", ""baab""].
     * Given s = ""abc"", return []."
     * "对于回文 只要构造前一半就行
     * 对于奇数的那个字符 只留一个放在中间即可 然后建立一个数组 里面存的就是备选的前一半的字符 然后dfs构造 注意要跳过连续的字符
     * 构造完了之后直接把前一半颠倒了添加即可"
     */
    public static List<String> generatePalindromes(String s) {
        int odd = 0;
        StringBuilder mid = new StringBuilder();
        List<String> res = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }
        // cannot form any palindromic string
        if (odd > 1)
            return res;
        // step 2. add half count of each character to list
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (val % 2 != 0)
                mid.append(key);
            for (int i = 0; i < val / 2; i++)
                list.add(key);
        }
        // step 3. generate all the permutations
        getPerm(list, mid.toString(), new boolean[list.size()], new StringBuilder(), res);
        return res;
    }

    // generate all unique permutation from list
    static void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb,
                        List<String> res) {
        if (sb.length() == list.size()) {
            // form the palindromic string
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            // avoid duplication
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1])
                continue;
            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                // recursion
                getPerm(list, mid, used, sb, res);
                // backtracking
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
