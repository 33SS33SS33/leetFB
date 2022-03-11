package aMaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Tags: Array, Backtracking, Bit Manipulation
 * 用DP 动态规划
 * [1,2] 和[1,2,3]的子集的差异在于 [1,2,3]是包含了[1,2]的所有子集再加上 把[1,2]所有的子集插入3元素  这两个加起来就是[1,2,3]的子集
 * 题目要求必须是非降序的 所以先将nums排序即可
 */
class Subsets {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5};
        List<List<Integer>> res2 = subsetsB(nums);
        for (List<Integer> l : res2) {
            System.out.println(l.toString());
        }
    }

    //所有backtrack
    // https://discuss.leetcode.com/topic/46159/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
    /**
     * Given a set of distinct integers, S, return all possible subsets.
     * Note:Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * If S = [1,2,3], a solution is:[[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
     */
    public static List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if (S == null || S.length == 0)
            return res;
        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> item = new ArrayList<>(res.get(j));
                item.add(S[i]);
                res.add(item);
            }
        }
        return res;
    }

    /**
     * Remember the start position and do backtracking
     */
    public static List<List<Integer>> subsetsB(int[] s) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(s);
        subsetsB(s, 0, new ArrayList<>(), res);
        return res;
    }

    public static void subsetsB(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        result.add(new ArrayList<>(set));
        for (int i = start; i < s.length; i++) {
            set.add(s[i]); // with i
            subsetsB(s, i + 1, set, result); // DFS
            set.remove(set.size() - 1); // remove last element
        }
    }

    //位运算
    //https://discuss.leetcode.com/topic/2764/my-solution-using-bit-manipulation/13
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        int totalNumber = 1 << S.length;
        List<List<Integer>> collection = new ArrayList<>(totalNumber);
        for (int i = 0; i < totalNumber; i++) {
            List<Integer> set = new LinkedList<>();
            for (int j = 0; j < S.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(S[j]);
                }
            }
            collection.add(set);
        }
        return collection;
    }


}
