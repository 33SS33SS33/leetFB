package aMaz;

/**
 * Created by shanshan on 1/26/19.
 */
public class ValidParenthesisString {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*',
     * write a function to check whether this string is valid.
     * We define the validity of a string by these rules:
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')'
     * or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     * Input: "()" Output: True
     * Input: "(*)" Output: True
     * Input: "(*))" Output: True
     */
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (count < 0) return false;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count <= 0) return false;
                count--;
            } else if (c == '*') {
                return check(s, i + 1, count + 1) ||
                        check(s, i + 1, count - 1) ||
                        check(s, i + 1, count);
            }
        }

        return count == 0;
    }
}
