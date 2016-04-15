package easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

 * Tags: Stack, String
 */
class ValidParenthese {
    public static void main(String[] args) {
        ValidParenthese v = new ValidParenthese();
        System.out.println(v.isValid("()"));
        System.out.println(v.isValidB("()"));
        System.out.println(v.isValidC("()"));
        System.out.println(v.isValid("()[]{}"));
        System.out.println(v.isValidB("()[]{}"));
        System.out.println(v.isValidC("()[]{}"));
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValidB("([)]"));
        System.out.println(v.isValidC("([)]"));
        System.out.println(v.isValid("[({(())}[()])]"));
        System.out.println(v.isValidB("[({(())}[()])]"));
        System.out.println(v.isValidC("[({(())}[()])]"));
        System.out.println(v.isValid("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
        System.out.println(v.isValidB("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
        System.out.println(v.isValidC("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
    }

    /**
     * creek!!  最好的
     */
    public static boolean isValidB(String s) {
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

    /**
     * Use a stack to store the parens If left paren, push to stack
     * Elif stk is empty, return false
     * Elif mathches, pop and go on
     * Else don't match, return false
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return false;
        Stack<Character> stk = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (!isParenthese(c))
                continue;
            if ("({[".indexOf(c) != -1) {
                stk.push(c);
            } else {
                if (!stk.isEmpty() && isMatch(stk.peek(), c)) {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }
    boolean isParenthese(char c) {
        String parens = "(){}[]";
        return parens.indexOf(c) != -1;
    }
    boolean isMatch(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }

    /**
     * ------------错的-----------
     */
    public static boolean isValidC(String s) {
        if (s == null || s.length() == 0)
            return false;
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stk.isEmpty()) {
                stk.push(c);
            } else if (stk.peek() == '(' && c == ')') {
                stk.pop();
            } else if (stk.peek() == '[' && c == ']') {
                stk.pop();
            } else if (stk.peek() == '{' && c == '}') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.isEmpty();
    }
}
