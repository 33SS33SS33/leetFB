package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/6.
 */

import java.util.*;

/**Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 Note: The input string may contain letters other than the parentheses ( and ).
 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
*/
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        String s = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        System.out.println(removeInvalidParentheses(s));
        System.out.println(removeInvalidParentheses(s2));
        System.out.println(removeInvalidParentheses(s3));
    }

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

    // helper function checks if string s contains valid parantheses
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
