package backTrac;

import java.util.*;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * Tags: Array, Backtracking, Bit Manipulation
 */
class Subsets {
    public static void main(String[] args) {
        int[] nums = { 1, 2};
        List<List<Integer>> res = subsetsA(nums);
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }
/*        List<List<Integer>> resB = subsetsB(nums);
        for (List<Integer> l : resB) {
            System.out.println(l.toString());
        }*/
/*        ArrayList<ArrayList<Integer>> res1 = subsets1(nums);
        for (List<Integer> l : res1) {
            System.out.println(l.toString());
        }
        ArrayList<ArrayList<Integer>> res2 = subsets2(nums);
        for (List<Integer> l : res2) {
            System.out.println(l.toString());
        }
        ArrayList<ArrayList<Integer>> res3 = subsets3(nums);
        for (List<Integer> l : res3) {
            System.out.println(l.toString());
        }*/
    }
    
    /**
     * Remember the start position and do backtracking
     */
    public static List<List<Integer>> subsetsB(int[] s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // Arrays.sort(s); // unneccessary
        subsetsB(s, 0, new ArrayList<Integer>(), res);
        return res;
    }
    public static void subsetsB(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(set));
        for (int i = start; i < s.length; i++) {
            set.add(s[i]); // with i
            subsetsB(s, i + 1, set, result); // DFS
            set.remove(set.size() - 1); // remove last element
        }
    }

    /**
     * Recursive down to two branches.
     */
    public static List<List<Integer>> subsetsA(int[] s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(s); // unneccessary
        subsetsA(s, 0, new ArrayList<Integer>(), res);
        return res;
    }
    public static void subsetsA(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        if (start == s.length) {
            result.add(set);
            return;
        }
        List<Integer> copy = new ArrayList<Integer>(set);
        subsetsA(s, start + 1, set, result); // without
        copy.add(s[start]);
        subsetsA(s, start + 1, copy, result); // with
    }

    /**递归 时间和空间都是取决于结果的数量，也就是O(2^n)*/
    public static ArrayList<ArrayList<Integer>> subsets1(int[] num) {
        if(num == null)
            return null;
        Arrays.sort(num);
        return helper(num, num.length-1);
    }
    private static ArrayList<ArrayList<Integer>> helper(int[] num, int index) {
        if (index == -1) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> elem = new ArrayList<Integer>();
            res.add(elem);
            return res;
        }
        ArrayList<ArrayList<Integer>> res = helper(num, index - 1);
        int size = res.size();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
            elem.add(num[index]);
            res.add(elem);
        }
        return res;
    }

    /**非递归*/
    public static ArrayList<ArrayList<Integer>> subsets2(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        if (S == null || S.length == 0)
            return res;
        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> item = new ArrayList<Integer>(res.get(j));
                item.add(S[i]);
                res.add(item);
            }
        }
        return res;
    }

    /**creek
     * Given a set S of n distinct integers, there is a relation between Sn and Sn-1.
     * The subset of Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}.
     * Therefore, a Java solution can be quickly formalized.*/
    public static ArrayList<ArrayList<Integer>> subsets3(int[] S) {
        if (S == null)
            return null;
        Arrays.sort(S);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < S.length; i++) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            //get sets that are already in result
            for (ArrayList<Integer> a : result) {
                temp.add(new ArrayList<Integer>(a));
            }
            //add S[i] to existing sets
            for (ArrayList<Integer> a : temp) {
                a.add(S[i]);
            }
            //add S[i] only as a set
            ArrayList<Integer> single = new ArrayList<Integer>();
            single.add(S[i]);
            temp.add(single);
            result.addAll(temp);
        }
        //add empty set
        result.add(new ArrayList<Integer>());
        return result;
    }
}
