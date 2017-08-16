package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/5/6.
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * DFS BFS
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        String s = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        System.out.println(removeInvalidParentheses(s));
        System.out.println(removeInvalidParentheses2(s));
        System.out.println(removeInvalidParentheses(s2));
        System.out.println(removeInvalidParentheses2(s2));
        System.out.println(removeInvalidParentheses(s3));
        System.out.println(removeInvalidParentheses2(s3));
    }

    //最好的
    public static List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0)
            return ans;
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    /**
     * 最好的  BFS
     */
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null)
            return res;
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        // initialize
        queue.add(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                res.add(s);// found an answer, add to the result
                found = true;
            }
            if (found)
                continue;
            for (int i = 0; i < s.length(); i++) {  // generate all possible states
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')')
                    continue;
                //为什么要substr
                String t = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    // DFS helper function checks if string s contains valid parantheses
    static boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            if (c == ')' && count-- == 0)
                return false;
        }
        return count == 0;
    }


}
