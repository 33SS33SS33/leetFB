package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by GAOSHANSHAN835 on 2015/12/14.
 * 错的------
 */
public class FindSubstring {
    public static void main(String[] args) {
        String S = "12344434300";
        String[] L = {"123", "444343"};

        System.out.println(new FindSubstring().findSubstring(S, L).toString());
    }

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (S == null || S.length() == 0 || L == null || L.length == 0)
            return res;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < L.length; i++) {
            if (map.containsKey(L[i])) {
                map.put(L[i], map.get(L[i]) + 1);
            } else {
                map.put(L[i], 1);
            }
        }
        for (int i = 0; i < L[0].length(); i++) {
            HashMap<String, Integer> curMap = new HashMap<String, Integer>();
            int count = 0;
            int left = i;
            for (int j = i; j <= S.length() - L[0].length(); j += L[0].length()) {
                String str = S.substring(j, j + L[0].length());
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str))
                        curMap.put(str, curMap.get(str) + 1);
                    else
                        curMap.put(str, 1);
                    if (curMap.get(str) <= map.get(str))
                        count++;
                    else {
                        while (curMap.get(str) > map.get(str)) {
                            String temp = S.substring(left, left + L[0].length());
                            if (curMap.containsKey(temp)) {
                                curMap.put(temp, curMap.get(temp) - 1);
                                if (curMap.get(temp) < map.get(temp))
                                    count--;
                            }
                            left += L[0].length();
                        }
                    }
                    if (count == L.length) {
                        res.add(left);
                        //if(left<)
                        String temp = S.substring(left, left + L[0].length());
                        if (curMap.containsKey(temp))
                            curMap.put(temp, curMap.get(temp) - 1);
                        count--;
                        left += L[0].length();
                    }
                } else {
                    curMap.clear();
                    count = 0;
                    left = j + L[0].length();
                }
            }
        }
        return res;
    }

    public String minWindow(String S, String T) {
        if (S == null || S.length() == 0)
            return "";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
            } else {
                map.put(T.charAt(i), 1);
            }
        }
        int left = 0;
        int count = 0;
        int minLen = S.length() + 1;
        int minStart = 0;
        for (int right = 0; right < S.length(); right++) {
            if (map.containsKey(S.charAt(right))) {
                map.put(S.charAt(right), map.get(S.charAt(right)) - 1);
                if (map.get(S.charAt(right)) >= 0) {
                    count++;
                }
                while (count == T.length()) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minStart = left;
                    }
                    if (map.containsKey(S.charAt(left))) {
                        map.put(S.charAt(left), map.get(S.charAt(left)) + 1);
                        if (map.get(S.charAt(left)) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        if (minLen > S.length()) {
            return "";
        }
        return S.substring(minStart, minStart + minLen);
    }

}
