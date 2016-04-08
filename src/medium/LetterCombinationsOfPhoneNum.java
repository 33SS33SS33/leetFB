package medium;

import java.util.*;

/**
 * Given a digit string, return all possible letter combinations that the
 * number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * Tags: Backtracking, String
 */
class LetterCombinationsOfPhoneNum {
    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNum().letterCombinationsA("23"));
        System.out.println(new LetterCombinationsOfPhoneNum().letterCombinationsB("23"));
    }

    private static final String[] LETTERS = { "",     // 0
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

    /**
     * Backtracking to generate all combinations
     */
    public List<String> letterCombinationsA(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null)
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

    public List<String> letterCombinationsB(String digits) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        map.put(0, "");
        ArrayList<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0)
            return result;
        ArrayList<Character> temp = new ArrayList<Character>();
        getString(digits, temp, result, map);
        return result;
    }
    public void getString(String digits, ArrayList<Character> temp, ArrayList<String> result,
            HashMap<Integer, String> map) {
        if (digits.length() == 0) {
            char[] arr = new char[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                arr[i] = temp.get(i);
            }
            result.add(String.valueOf(arr));
            return;
        }
        Integer curr = Integer.valueOf(digits.substring(0, 1));
        String letters = map.get(curr);
        for (int i = 0; i < letters.length(); i++) {
            temp.add(letters.charAt(i));
            getString(digits.substring(1), temp, result, map);
            temp.remove(temp.size() - 1);
        }
    }

}
