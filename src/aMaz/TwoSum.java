package aMaz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * Tags: Array, HashTable
 */
class TwoSum {
    public static void main(String[] args) {
        TwoSum t = new TwoSum();
        int[] numbers = {4, 2, 7}; // 6 = 2+4
        int target = 6;
        int[] res = t.twoSum(numbers, target);
        int[] res1 = t.twoSumD(numbers, target);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }
        System.out.println();
        for (int i = 0; i < res1.length; i++) {
            System.out.print(i == res1.length - 1 ? res1[i] : res1[i] + ", ");
        }
    }

    /**
     * One-pass Hash Table 最好的
     * Complexity Analysis:
     * Time complexity : O(n)O(n). We traverse the list containing nn elements only once.
     * Each look up in the table costs only O(1)O(1) time.
     * <p>
     * Space complexity : O(n)O(n). The extra space required depends on the number of items stored in the hash table,
     * which stores at most nn elements.
     */
    public int[] twoSumA(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 在LeetCode原题中是假设结果有且仅有一个的，一般来说面试时会要求出所有的结果，
     * 这个时候会涉及到重复pair的处理，相关的内容会在3Sum那道题目中
     * HashMap, O(n) time, O(n) space
     * key -> number, value -> index
     * Search new target in map and return index if not same
     * Otherwise return null
     */
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) { // put all to map
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int newTarget = target - numbers[i];
            // O(1) searchinRotatedSortedArrayb
            if (map.containsKey(newTarget) && i != map.get(newTarget)) { // can't be same indices
                return new int[]{i + 1, map.get(newTarget) + 1};
            }
        }
        return null;
    }

    /**
     * 在这里，输出结果改成了满足相加等于target的两个数，而不是他们的index。
     * 该算法的时间复杂度是O(nlogn+n)=O(nlogn)，空间复杂度取决于排序算法
     */
    public int[] twoSumD(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers == null || numbers.length < 2)
            return null;
        Arrays.sort(numbers);
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                res[0] = numbers[l];
                res[1] = numbers[r];
                return res;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

}