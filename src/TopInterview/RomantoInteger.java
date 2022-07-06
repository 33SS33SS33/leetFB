package TopInterview;

import java.util.HashMap;
import java.util.Map;

class RomantoInteger {
    public static void main(String[] args) {
        String s = "X";
        System.out.println(new RomantoInteger().romantoInteger(s));
    }

    /**
     * Given a roman numeral, zigZagConversion it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     * Input: "III"  Output: 3 Input: "IV"  Output: 4
     * Input: "IX"  Output: 9 Input: "LVIII"  Output: 58 Explanation: L = 50, V= 5, III = 3.
     * Tags: Math, String
     */
    public int romantoInteger(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> m = new HashMap<>();
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
