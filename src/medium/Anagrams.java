package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * Note: All inputs will be in lower-case.
 * Tags: HashTable, String
 */
class Anagrams {
    public static void main(String[] args) {
        String[] strs = {"dog", "dot", "cog", "log", "god", "tod"};
        System.out.println(anagrams(strs).toString());
    }

    public static List<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return result;
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String t = String.valueOf(arr);
            if (map.get(t) == null) {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(t, l);
            } else {
                map.get(t).add(i);
            }
        }
        for (ArrayList<Integer> l : map.values()) {
            if (l.size() > 1) {
                for (Integer i : l) {
                    result.add(strs[i]);
                }
            }
        }
        return result;
    }

}
