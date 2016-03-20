package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/3/2.
 */
public class LongestSubstringContainsKUniqChar {

    public static void main(String[] args) {
        LongestSubstringContainsKUniqChar m = new LongestSubstringContainsKUniqChar();
        System.out.println(m.maxSubString2UniqueCharsB("abcbbbbcccbdddadacb"));

        /**-------错的-------*/
        System.out.println(m.maxSubStringKUniqueCharsA("bbbbb",2));
        System.out.println(m.maxSubStringKUniqueCharsB("bbbbb",2));
        System.out.println(m.maxSubStringKUniqueCharsA("fdjskajfhhh",2));
        System.out.println(m.maxSubStringKUniqueCharsB("fdjskajfhhh",2));
        System.out.println(m.maxSubStringKUniqueCharsA("fdjskajfhhh",3));
        System.out.println(m.maxSubStringKUniqueCharsB("fdjskajfhhh",3));
        System.out.println(m.maxSubStringKUniqueCharsA("fdjskajfhh",2));
        System.out.println(m.maxSubStringKUniqueCharsB("fdjskajfhh",2));
    }
    public static String maxSubString2UniqueCharsB(String s) {
        int maxLen = 0;
        String maxSubstring = null;
        int m = 0;
        // declare a map to track the right most position of the two characters
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // if map contains 2 characters, process
            if (map.size() == 2 && !map.containsKey(c)) {
                if (i - m > maxLen) {
                    maxLen = i - m;
                    maxSubstring = s.substring(m, i);
                }
                // get the left most index for
                int leftMost = s.length();
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() < leftMost) {
                        leftMost = entry.getValue();
                    }
                }
                m = leftMost + 1;
                map.remove(s.charAt(leftMost));
            }
            map.put(c, i);
        }
        if (map.size() == 2 && maxLen == 0) {
            return s;
        }
        return maxSubstring;
    }

    /**navie Solution for K Unique Characters   The time is O(n*k).*/
    public static String maxSubStringKUniqueCharsA(String s, int k) {
        int maxLen = 0;
        String maxSubstring = null;
        int m = 0;
        // declare a map to track the right most position of the two characters
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // if map contains 2 characters, process
            if (map.size() == k && !map.containsKey(c)) {
                if (i - m > maxLen) {
                    maxLen = i - m;
                    maxSubstring = s.substring(m, i);
                }
                int leftMost = s.length();
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() < leftMost) {
                        leftMost = entry.getValue();
                    }
                }
                m = leftMost + 1;
                map.remove(s.charAt(leftMost));
            }
            map.put(c, i);
        }
        if (map.size() == k && maxLen == 0) {
            return s;
        }
        return maxSubstring;
    }


    /**Better Solution for K Unique Characters   Time is O(n).*/
    public static String maxSubStringKUniqueCharsB(String s, int k) {
        //declare a counter
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int start = 0;
        int maxLen = 0;
        String maxSubstring = null;

        for (int i = 0; i < s.length(); i++) {
            //add each char to the counter
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
            if(map.size() == k+1){
                //get maximum
                int range = i-start;
                if(range > maxLen){
                    maxLen = range;
                    maxSubstring = s.substring(start, i);
                }
                //move left cursor toward right, so that substring contains only k chars
                while(map.size()>k){
                    char first = s.charAt(start);
                    int count = map.get(first);
                    if(count>1){
                        map.put(first,count-1);
                    }else{
                        map.remove(first);
                    }
                    start++;
                }
            }
        }
        if (map.size() == k && maxLen == 0) {
            return s;
        }
        return maxSubstring;
    }
}
