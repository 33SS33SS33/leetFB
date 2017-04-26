package medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * Note: All inputs will be in lower-case.
 * Tags: Hashtable, String
 */
class Anagrams {
    public static void main(String[] args) {
        String[] strs = {"dog", "dot", "cog", "log", "god", "tod"};
        System.out.println(anagrams(strs).toString());
        System.out.println(anagrams(strs).toString());
        System.out.println(anagramsB(strs).toString());
    }

    /**
     * Use map<String, Integer>
     * Integer is initialized as the index, updated to -1 when the word is
     * added to map to make sure that no duplicate situation happens
     */
    public static List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return res;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < strs.length; i++) { // traverse the array
            /*generate key*/
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String key = new String(word);
            if (map.containsKey(key)) {
                res.add(strs[i]); // add this string
                if (map.get(key) >= 0) { // key string not added
                    res.add(strs[map.get(key)]);
                    map.put(key, -1); // mark already added as -1
                }
            } else
                map.put(key, i); // first put sorted string and index
        }
        return res;
    }

    /**
     * creek
     */
    public static List<String> anagramsB(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return result;
        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String t = String.valueOf(arr);
            if (map.get(t) == null) {
                ArrayList<Integer> l = new ArrayList<Integer>();
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
