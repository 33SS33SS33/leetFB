package aMaz;

import java.util.LinkedList;

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
        if (s == null || s.length() == 0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    max = stack.isEmpty() ?
                            Math.max(max, i - start + 1) : Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

}
