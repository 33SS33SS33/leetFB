package aMaz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class LetterCombinationsOfPhoneNum {
    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNum().letterCombinations("23"));
        System.out.println(new LetterCombinationsOfPhoneNum().letterCombinationsA("23"));
    }

    /**
     * Backtracking to generate all combinations
     * Given a digit string, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * Note: Although the above answer is in lexicographical order, your answer could be
     * in any order you want.
     * Tags: Backtracking, String
     */
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

    /**
     * 迭代
     * https://discuss.leetcode.com/topic/8465/my-java-solution-with-fifo-queue/6
     */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                //看不懂这里
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

}
