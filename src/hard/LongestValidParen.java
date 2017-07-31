package hard;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')'
 * find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length  * = 2.
 * Another example is ")()())", where the longest valid parentheses substring
 * is "()()", which has length = 4.
 * Follow up:
 * What if there are curly bracs and brakets as well? {} []?
 * Tags: DP, String
 */
class LongestValidParen {
    public static void main(String[] args) {
        System.out.println(longestValidParenthesesD("(()")); // 2
        System.out.println(longestValidParenthesesD("((((((")); // 0
        System.out.println(longestValidParenthesesD(")()()")); // 4
    }

    /**
     * 最好的
     * Build a stack for indices of open parentheses
     * Traverse the string, if current is open paren, push to stack
     * Otherwise, its close paren.
     * If stack is empty, no open paren left, reset len to 0.
     * If not, pop the index from stack, matchedLen = current index - index of
     * pop open paren + 1
     * If stack is empty, means this matchedLen can be added to the whole len
     * If not,
     */
    public static int longestValidParenthesesD(String s) {
        if (s == null || s.length() == 0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
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
                    max = stack.isEmpty() ? Math.max(max, i - start + 1) : Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

}
