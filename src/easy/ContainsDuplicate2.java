package easy;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the difference between i and j is at most k.
 */

/**
 * 需要记录一下上一个重复元素最近的坐标
 */
public class ContainsDuplicate2 {
    public static void main(String[] args) {
        int[] num = { 1, 3, 7, 5, 8 };
        int[] num2 = { 1, 3, 7, 3, 8 };
        System.out.println(containsNearbyDuplicate(num, 3));
        System.out.println(containsNearbyDuplicateB(num, 3));
        System.out.println(containsNearbyDuplicateC(num, 3));
        System.out.println(containsNearbyDuplicate(num2, 3));
        System.out.println(containsNearbyDuplicateB(num2, 3));
        System.out.println(containsNearbyDuplicateC(num2, 3));
    }

    /**
     * -------better----
     */
    public static boolean containsNearbyDuplicateC(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int pre = map.get(nums[i]);
                if (i - pre <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k <= 0)
            return false;
        Map<Integer, List<Integer>> pairs = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> l = pairs.get(nums[i]);
            if (l == null) {
                l = new ArrayList<Integer>();
                pairs.put(nums[i], l);
            } else {
                for (int j : l) {
                    if (i - j <= k) {
                        return true;
                    }
                }
            }
            l.add(i);
        }
        return false;
    }

    public static boolean containsNearbyDuplicateB(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int preIndex = map.get(nums[i]);
                int gap = i - preIndex;
                min = Math.min(min, gap);
            }
            map.put(nums[i], i);
        }

        if (min <= k) {
            return true;
        } else {
            return false;
        }
    }

}
