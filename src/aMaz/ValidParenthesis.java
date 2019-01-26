package aMaz;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Tags: Stack, String
 */
class ValidParenthesis {
    public static void main(String[] args) {
        ValidParenthesis v = new ValidParenthesis();
        System.out.println(v.validParenthesisString("()"));
        System.out.println(v.validParenthesisString("()[]{}"));
        System.out.println(v.validParenthesisString("([)]"));
        System.out.println(v.validParenthesisString("[({(())}[()])]"));
        System.out.println(v.validParenthesisString("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
    }

    /**
     * creek!!  最好的
     */
    public static boolean validParenthesisString(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (map.keySet().contains(curr)) {
                stack.push(curr);
            } else if (map.values().contains(curr)) {
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

}
