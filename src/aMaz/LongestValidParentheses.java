package aMaz;

import java.util.Stack;

//TODO
class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()")); // 2
        System.out.println(longestValidParentheses("((((((")); // 0
        System.out.println(longestValidParentheses(")()()")); // 4
    }

    /**
     * HARD  Build a stack for indices of open parentheses
     * Traverse the string, if current is open paren, push to stack
     * Otherwise, its close paren.
     * If stack is empty, no open paren left, reset len to 0.
     * If not, pop the index from stack, matchedLen = current index - index of
     * pop open paren + 1
     * <p>
     * Given a string containing just the characters '(' and ')'
     * find the length of the longest valid (well-formed) parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length  * = 2.
     * Another example is ")()())", where the longest valid parentheses substring
     * is "()()", which has length = 4.
     * Follow up:
     * What if there are curly bracs and brakets as well? {} []?
     * Tags: DP, String
     */
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

}
