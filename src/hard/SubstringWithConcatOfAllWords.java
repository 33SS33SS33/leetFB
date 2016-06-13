package hard;

import java.util.*;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 * Tags: Hash Table, Two Pointers, String
 */
class SubstringWithConcatOfAllWords {
    public static void main(String[] args) {
        String S = "barfoothefoobarman";
        String[] L = new String[] { "foo", "bar" };
        System.out.print(findSubstringA(S, L).toString());
        System.out.print(findSubstringB(S, L).toString());
    }

    /**
     * Build a map for words in L and its relative counts  最好的
     * At first I was gonna to use a set for words.
     * Owing to the fact that duplicate is allowed in L, we need to use map instead.
     */
    public static List<Integer> findSubstringA(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0)
            return res;
        int len = L[0].length(); // length of each word
        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : L)
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        for (int i = 0; i <= S.length() - len * L.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < L.length; j++) { // check if match
                String str = S.substring(i + j * len, i + j * len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1)
                        copy.remove(str);
                    else
                        copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else
                    break; // not in L
            }
        }
        return res;
    }

    /**
     * creek Since each word in the dictionary has the same length, each of them can be treated as a single character.
     */
    public static List<Integer> findSubstringB(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        //frequency of words
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String w : words) {
            if (map.containsKey(w)) {
                map.put(w, map.get(w) + 1);
            } else {
                map.put(w, 1);
            }
        }
        int len = words[0].length();
        for (int j = 0; j < len; j++) {
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            int start = j;//start index of start
            int count = 0;//count totoal qualified words so far
            for (int i = j; i <= s.length() - len; i = i + len) {
                String sub = s.substring(i, i + len);
                if (map.containsKey(sub)) {
                    //set frequency in current map
                    if (currentMap.containsKey(sub)) {
                        currentMap.put(sub, currentMap.get(sub) + 1);
                    } else {
                        currentMap.put(sub, 1);
                    }
                    count++;
                    while (currentMap.get(sub) > map.get(sub)) {
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);
                        count--;
                        start = start + len;
                    }
                    if (count == words.length) {
                        result.add(start); //add to result
                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);
                        count--;
                        start = start + len;
                    }
                } else {
                    currentMap.clear();
                    start = i + len;
                    count = 0;
                }
            }
        }
        return result;
    }
}
