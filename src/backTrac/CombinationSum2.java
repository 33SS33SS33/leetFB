package backTrac;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find
 * all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used <strong>once</strong> in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 * Tags: Array, Backtracking
 */
class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int tar = 8;
        List<List<Integer>> solution = new CombinationSum2().combinationSum2a(candidates, tar);
        for (List<Integer> l : solution)
            System.out.println(l.toString());

        List<List<Integer>> solution2 = new CombinationSum2()
                .combinationSum2aa(candidates, tar);
        for (List<Integer> l : solution2)
            System.out.println(l.toString());
    }

    public List<List<Integer>> combinationSum2aa(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum2a(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }

    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        if (target < 0)
            return;
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1])
                continue;
            path.add(path.size(), cand[i]);
            dfs_com(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);
        }
    }

}
