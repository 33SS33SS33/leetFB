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
        System.out.println(permutea(new int[]{1, 3, 2}));
    }

    /**
     * 最好的
     */
    public static List<List<Integer>> permutea(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i]))
                    continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}