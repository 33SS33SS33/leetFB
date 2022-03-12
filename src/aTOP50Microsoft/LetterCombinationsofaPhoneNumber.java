package aTOP50Microsoft;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
    private static final String[] LETTERS = {"",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public List<String> letterCombinationsA(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0)
            return res;
        helper(digits, 0, "", res);
        return res;
    }

    private void helper(String digits, int s, String comb, List<String> res) {
        if (s == digits.length()) { // all digits done, stop
            res.add(comb);
            return;
        }
        String c = LETTERS[digits.charAt(s) - '0']; // note how to get int index
        for (int i = 0; i < c.length(); i++) { // note its i starts from 0
            helper(digits, s + 1, comb + c.charAt(i), res); // backtracking
        }
    }
}
