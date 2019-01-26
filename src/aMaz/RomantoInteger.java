package aMaz;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * Example 1:
 * Input: "III"
 * Output: 3
 * Example 2:
 * Input: "IV"
 * Output: 4
 * Example 3:
 * Input: "IX"
 * Output: 9
 * Example 4:
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Tags: Math, String
 */
class RomantoInteger {
    public static void main(String[] args) {
        String s = "X";
        System.out.println(new RomantoInteger().romantoInteger(s));
    }

    //
    public int romantoInteger(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) { // backwards
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i)))
                result += m.get(s.charAt(i));
            else
                result -= m.get(s.charAt(i));
        }
        return result;
    }

}
