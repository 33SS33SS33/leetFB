package TopInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
    private List<String> combinations = new ArrayList<>();
   /* private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
*/
    HashMap<Character, String> letters = new HashMap<>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return combinations;
        }
        backtrack(digits,0, new StringBuilder());
        return combinations;
    }

    private void backtrack(String digits,int index, StringBuilder path) {
        if (path.length() == digits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }
        // Get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(digits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(digits,index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
