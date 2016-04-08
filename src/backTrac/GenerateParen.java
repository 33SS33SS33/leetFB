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
        System.out.println(generateParenthesisA(3));
        System.out.println(generateParenthesisB(3));
        System.out.println(generateParenthesisC(3));
    }

    /**Backtracking
     * Helper function use left and right to represent available parentheses
     * Initialize left as n, right as 0
     */
    public static List<String> generateParenthesisA(int n) {
        List<String> ans = new ArrayList<String>();
        if (n <= 0) return ans;
        dfs(n, 0, "", ans);
        return ans;
    }
    /**
     * @param left available left parentheses
     * @param right available right parentheses
     * @param res current result
     * @param ans the answer list of the problem
     */
    public static void dfs(int left, int right, String res, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(res);
            return;
        }
        if (left > 0) dfs(left - 1, right + 1, res + "(", ans); // add (, right + 1
        if (right > 0) dfs(left, right - 1, res + ")", ans); // add ), right - 1
    }

    public static List<String> generateParenthesisB(int n) {
        if (n == 0) return new ArrayList<String>();
        if (n == 1) return Arrays.asList(new String[]{"()"});
        HashSet<String> temp = new HashSet<String>();
        for(String s : generateParenthesisB(n - 1)){
            temp.add("(" + s + ")");
            temp.add("()" + s);
            temp.add(s + "()");
        }
        for(int i = 2; i < n - 1 ; i++){
            for(String s : generateParenthesisB(n - i)){
                for(String ss : generateParenthesisB(i)){
                    temp.add(ss + s);
                    temp.add(s + ss);
                }
            }
        }
        return new ArrayList<String>(temp);
    }

    /**creek  This solution looks more complicated. ,You can use n=2 to walk though the code.*/
    public static List<String> generateParenthesisC(int n) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Integer> diff = new ArrayList<Integer>();
        result.add("");
        diff.add(0);
        for (int i = 0; i < 2 * n; i++) {
            ArrayList<String> temp1 = new ArrayList<String>();
            ArrayList<Integer> temp2 = new ArrayList<Integer>();
            for (int j = 0; j < result.size(); j++) {
                String s = result.get(j);
                int k = diff.get(j);
                if (i < 2 * n - 1) {
                    temp1.add(s + "(");
                    temp2.add(k + 1);
                }
                if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
                    temp1.add(s + ")");
                    temp2.add(k - 1);
                }
            }
            result = new ArrayList<String>(temp1);
            diff = new ArrayList<Integer>(temp2);
        }
        return result;
    }

}