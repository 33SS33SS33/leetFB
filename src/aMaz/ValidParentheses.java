package aMaz;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
 /*       Scanner in = new Scanner(System.in);
        String s = in.next();*/
        System.out.println(validParentheses("{[]}"));
        System.out.println(isValidParentheses("([)]"));
    }

    public static boolean validParentheses(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> mystack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char buff = s.charAt(i);
            if (buff == '(' || buff == '{' || buff == '[') {
                mystack.push(buff);
            } else {
                if (mystack.isEmpty()) {
                    return false;
                } else if ((buff == ')' && mystack.peek() != '(') || (buff == ']' && mystack.peek() != '[') || (buff == '}' && mystack.peek() != '{')) {
                    return false;
                } else {
                    mystack.pop();
                }
            }
        }
        if (mystack.empty()) {
            return true;
        } else {
            return false;
        }
    }

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
