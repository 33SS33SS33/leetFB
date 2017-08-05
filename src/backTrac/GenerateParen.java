package backTrac;

import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * Tags: Backtracking. String
 */
class GenerateParen {
    public static void main(String[] args) {
        System.out.println(generateParenthesisa(3));
        System.out.println(generateParenthesis(3));
    }

    /**
     * 最好的
     */
    public static List<String> generateParenthesisa(int n) {
        List<String> list = new ArrayList<String>();
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

    /**
     * My method is DP. First consider how to get the result f(n) from previous result
     * f(0)...f(n-1). Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "("
     * always at the first position, to produce a valid result, we can only put ")" in a way
     * that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra
     * pair.
     * Let us consider an example to get clear view:
     * f(0): ""
     * f(1): "("f(0)")"
     * f(2): "("f(0)")"f(1), "("f(1)")"
     * f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
     * So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-
     * 1)")"
     */
    public static List<String> generateParenthesis(int n) {
        List<List<String>> lists = new ArrayList<List<String>>();
        lists.add(Collections.singletonList(""));
        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<String>();
            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }

}