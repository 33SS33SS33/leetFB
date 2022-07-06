package aMaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Tags: Array, Two Pointers
 */
class ThreeSum {
    public static void main(String[] args) {
        int[] s = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res1 = new ThreeSum().threeSum(s);
        System.out.println(res1.toString());
    }

    /**
     * * Given an array S of n integers, are there elements a, b, c in S such that a
     * + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     * Note:
     * Elements in a triplet (a,b,c) must be in <strong>non-descending</strong> order. (ie, a ≤ b ≤ c)
     * The solution set must not contain <strong>duplicate</strong> triplets.
     * For example, given array S = {-1 0 1 2 -1 -4}, solution set is: (-1, 0, 1) (-1, -1, 2)
     * Two Pointers.  O(N^2) Java solution Sort given array first.
     * Traverse the array with 1 pointer
     * Use 2 more pointers from both start(i + 1) and end to find target
     * http://blog.csdn.net/linhuanmars/article/details/19711651
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        return res;
    }

    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }

}
