package backTrac;

import java.util.List;
import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * 
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 
 * Tags: Backtracking
 */
class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> lists = combineA(4, 2);
        for (List<Integer> l : lists) {
            System.out.print(l.toString());
        }
        System.out.println();
//        List<List<String>> lists2 = combineB(4, 2);
//        for (List<String> l : lists2) {
//            System.out.print(l.toString());
//        }
        System.out.println();
        ArrayList<ArrayList<Integer>> lists3 = combineC(4, 2);
        for (ArrayList<Integer> l : lists3) {
            System.out.print(l.toString());
        }
    }

    /** 最好的
     * Ascending order, track start  DFS
     */
    public static List<List<Integer>> combineA(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        combine(n, k, 1, new ArrayList<Integer>(), res); // note that start is 1
        return res;
    }
    public static void combine(int n, int k, int start, List<Integer> comb, List<List<Integer>> result) {
        if (k == 0) { // is a solution
            result.add(comb);
            return;
        }
        for (int i = start; i <= n; i++) { // note that from start to n, <=
            List<Integer> copy = new ArrayList<Integer>(comb);
            copy.add(i); // don't forget to add i to copy
            combine(n, k - 1, i + 1, copy, result); // choose k-1 from i+1 to n
        }
    }

    /**creek DFS*/
    public static ArrayList<ArrayList<Integer>> combineC(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (n <= 0 || n < k)
            return result;
        ArrayList<Integer> item = new ArrayList<Integer>();
        dfs(n, k, 1, item, result); // because it need to begin from 1
        return result;
    }
    private static void dfs(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
        if (item.size() == k) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i <= n; i++) {
            item.add(i);
            dfs(n, k, i + 1, item, res);
            item.remove(item.size() - 1); //?
        }
    }

    /**
     * From back to start
     */
/*    public static List<List<String>> combineB(int n, int k) {
        List<List<String>> res = new ArrayList<List<String>>();
        combine(n, k, new ArrayList<String>(), res);
        return res;
    }
    public static void combine(int n, int k, List<String> comb, List<List<String>> result) {
        if (k == 0) {
            result.add(comb); 
            return;
        }
        if (n <= k) { // choose all
            for (int i = n; i > 0; i--) {
                comb.add(i + "");
            }
            result.add(comb);
            return;
        }
        // with n, choose k-1 from n-1
        List<String> combWithN = new ArrayList<String>(comb);
        combWithN.add(n + "");
        combine(n - 1, k - 1, combWithN, result);
        // without n, choose k from n-1
        combine(n - 1, k, comb, result);
    }*/

}
