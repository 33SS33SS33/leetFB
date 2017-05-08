package aFB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * Elements in a triplet (a,b,c) must be in <strong>non-descending</strong>
 * order.
 * (ie, a ≤ b ≤ c)
 * The solution set must not contain <strong>duplicate</strong> triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 * Tags: Array, Two Pointers
 */
class ThreeSum {
    public static void main(String[] args) {
        int[] s = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res1 = threeSuma(s);
        List<List<Integer>> res = threeSum(s);
        System.out.println(res1.toString());
        System.out.println(res.toString());
    }

    /**
     * Two Pointers.  O(N^2) Java solution
     * Sort given array first.
     * Traverse the array with 1 pointer
     * Use 2 more pointers from both start(i + 1) and end to find target
     */
    public static List<List<Integer>> threeSuma(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3)
            return result;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // Skip same results
            int target = 0 - nums[i];
            int j = i + 1, k = len - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1])
                        j++; // Skip same results
                    while (j < k && nums[k] == nums[k - 1])
                        k--; // Skip same results
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }


    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue; // skip duplicate
            if (num[i] > 0)
                break; // stop at positive integers
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) { // skip duplicate
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0)
                    break;// already bigger than 0
                if (num[i] + num[j] + num[k] < 0)
                    j++;
                else if (num[i] + num[j] + num[k] > 0)
                    k--;
                else { // num[i] + num[j] + num[k] == 0
                    res.add(Arrays.asList(num[i], num[j], num[k]));
                    j++; // move j ahead
                    k--;
                }
            }
        }
        return res;
    }

}
