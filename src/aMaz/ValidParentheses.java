package aMaz;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
 /*       Scanner in = new Scanner(System.in);
        String s = in.next();*/
        System.out.println(isValidParentheses("{[]}"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValidParentheses("([)]"));
        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**最好的*/
    public static boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && validParentheses(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean validParentheses(char a, char b) {
        return (a == '[' && b == ']') ||
                (a == '{' && b == '}') ||
                (a == '(' && b == ')');
    }
}
