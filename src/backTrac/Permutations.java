package backTrac;

import java.util.*;

/**
 * Given a collection of numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * Tags: Backtracking
 */
class Permutations {
    public static void main(String[] args) {
        System.out.println(permuteB(new int[] { 1, 3, 2 }));
        System.out.println(new Permutations().permuteA(new int[] { 1, 3, 2 }));
    }
    /** 最好的
     * creek--
     */
    public ArrayList<ArrayList<Integer>> permuteA(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        //start from an empty list
        result.add(new ArrayList<Integer>());
        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size() + 1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);
                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);
                    // - remove num[i] add
                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<Integer>>(current);
        }
        return result;
    }
    /**
     * creek--recursively solve this problem. Swap each element with each element after it.
     */
    public static List<List<Integer>> permuteB(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        permute(num, 0, res);
        return res;
    }

    public static void permute(int[] num, int level, List<List<Integer>> res) {
        if (level == num.length) {
            List<Integer> row = new ArrayList<Integer>();
            for (int a : num)
                row.add(a);
            res.add(row);
            return;
        }
        for (int i = level; i < num.length; i++) {
            swap(num, level, i);
            permute(num, level + 1, res);
            swap(num, level, i); // reset
        }
    }

    public static void swap(int[] num, int i, int j) {
        if (i == j)
            return;
        num[i] = num[j] - num[i];
        num[j] = num[j] - num[i];
        num[i] = num[j] + num[i];
    }

    public ArrayList<ArrayList<Integer>> permuteC(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0)
            return res;
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int[] num, boolean[] used, ArrayList<Integer> item,
                        ArrayList<ArrayList<Integer>> res) {
        if (item.size() == num.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (!used[i]) {
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size() - 1);
                used[i] = false;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> permuteD(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0)
            return res;
        ArrayList<Integer> first = new ArrayList<Integer>();
        first.add(num[0]);
        res.add(first);
        for (int i = 1; i < num.length; i++) {
            ArrayList<ArrayList<Integer>> newRes = new ArrayList<ArrayList<Integer>>();
            for (int j = 0; j < res.size(); j++) {
                ArrayList<Integer> cur = res.get(j);
                for (int k = 0; k < cur.size() + 1; k++) {
                    ArrayList<Integer> item = new ArrayList<Integer>(cur);
                    item.add(k, num[i]);
                    newRes.add(item);
                }
            }
            res = newRes;
        }
        return res;
    }
}