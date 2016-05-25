package backTrac;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, S, return all
 * possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 * <p/>
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p/>
 * Tags: Array, Backtracking
 */

/**
 * 使用DFS来做
 * 每次都以不同的元素开始dfs 如果元素相同 则跳过
 * 因为元素相同的只需要在path上加上就行了
 * 应该也能用之前的办法做 待确认
 */
class SubsetsWithDup {
    public static void main(String[] args) {
        int[] num = { 1, 2, 2 };
        List<List<Integer>> res = subsetsWithDup(num);
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }
        System.out.print("------------------");
        List<List<Integer>> res2 = subsetsWithDupB(num);
        for (List<Integer> l2 : res2) {
            System.out.println(l2.toString());
        }
        System.out.print("------------------");
        ArrayList<ArrayList<Integer>> res3 = subsetsWithDupC(num);
        for (List<Integer> l2 : res3) {
            System.out.println(l2.toString());
        }
    }

    /**
     * Backtrack to generate all subsets
     */
    public static List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (null == num || num.length == 0)
            return res;
        Arrays.sort(num); // sort first
        subsetsHelper(res, new ArrayList<Integer>(), num, 0);
        return res;
    }

    /**
     * Add list to result
     * Backtrack from current position to the end of array
     * Skip duplicates first
     * Add number to list and pass list and i+1 to next backtrack
     * Reset list after that
     */
    private static void subsetsHelper(List<List<Integer>> res, List<Integer> list, int[] num,
            int pos) {
        res.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {
            if (i != pos && num[i] == num[i - 1])
                continue; // skip dups
            list.add(num[i]);
            subsetsHelper(res, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * if a number from S is the first one of the numbers with the same value,
     * it can be used to extend all previous subsets and create new
     * non-duplicate subsets.
     * if a number from S is a duplicated number of some value, it cannot be
     * used to extend all previous subsets. Only part of them. The idea is that
     * this number should help make some different subsets than its
     * predecessor. So it only needs to extend subsets which contains its
     * predecessor.
     * [1 2 2]
     * [ ], [1], [2], [1 2]
     * [1 2 2], [2 2] (add 2 to subsets which have 2)
     */
    public static List<List<Integer>> subsetsWithDupB(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); // empty set
        if (null == num || num.length == 0)
            return res;
        Arrays.sort(num); // sort first
        int j, prevSize = 0;
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) // dup number
                j = prevSize; // # of previous sets before last number
            else
                j = 0;
            prevSize = res.size(); // # of previous sets
            /*add to previous sets with same num*/
            for (; j < prevSize; j++) {
                List<Integer> temp = new ArrayList<Integer>(res.get(j));
                temp.add(num[i]);
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * creek ----
     */
    public static ArrayList<ArrayList<Integer>> subsetsWithDupC(int[] num) {
        if (num == null)
            return null;
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();
        for (int i = num.length - 1; i >= 0; i--) {
            //get existing sets
            if (i == num.length - 1 || num[i] != num[i + 1] || prev.size() == 0) {
                prev = new ArrayList<ArrayList<Integer>>();
                for (int j = 0; j < result.size(); j++) {
                    prev.add(new ArrayList<Integer>(result.get(j)));
                }
            }
            //add current number to each element of the set
            for (ArrayList<Integer> temp : prev) {
                temp.add(0, num[i]);
            }
            //add each single number as a set, only if current element is different with previous
            if (i == num.length - 1 || num[i] != num[i + 1]) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(num[i]);
                prev.add(temp);
            }
            //add all set created in this iteration
            for (ArrayList<Integer> temp : prev) {
                result.add(new ArrayList<Integer>(temp));
            }
        }
        //add empty set
        result.add(new ArrayList<Integer>());
        return result;
    }
}