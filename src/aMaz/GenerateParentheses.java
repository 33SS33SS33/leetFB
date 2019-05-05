package aMaz;

import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * Tags: Backtracking. String
 */
class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesisa(3));
    }

    /**
     * The idea here is to only add '(' and ')' that we know will
     * guarantee us a solution (instead of adding 1 too many close).
     * Once we add a '(' we will then discard it and try a ')'
     * which can only close a valid '('. Each of these steps are recursively called.
     */
    public static List<String> generateParenthesisa(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public static void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }
        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

}