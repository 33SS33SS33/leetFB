package aMaz;

import java.util.ArrayList;
import java.util.List;

class Permutations {
    public static void main(String[] args) {
        System.out.println(permutations(new int[]{1, 3, 2}));
    }

// https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    /**
     * Given a collection of numbers, return all possible permutations.
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     * Tags: Backtracking
     */
    public static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i]))
                continue; // element already exists, skip
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);//!!
            tempList.remove(tempList.size() - 1);
        }
    }

}