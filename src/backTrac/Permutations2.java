package backTrac;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 * Tags: Backtracking
 */
class Permutations2 {
    public static void main(String[] args) {
        List<List<Integer>> res1 = permutations2(new int[]{1, 2, 3});
        for (List<Integer> l : res1)
            System.out.println(l);
    }

    /**
     * 最好的
     */
    public static List<List<Integer>> permutations2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                    continue; //why
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}
