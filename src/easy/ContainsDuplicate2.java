package easy;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the difference between i and j is at most k.
 * 需要记录一下上一个重复元素最近的坐标
 */
public class ContainsDuplicate2 {
    public static void main(String[] args) {
        int[] num = {1, 3, 7, 5, 8};
        int[] num2 = {1, 3, 7, 3, 8};
        System.out.println(containsNearbyDuplicatea(num, 3));
        System.out.println(containsNearbyDuplicatea(num2, 3));
    }

    /**
     * 最好的
     */
    public static boolean containsNearbyDuplicatea(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k)
                set.remove(nums[i - k - 1]);
            if (!set.add(nums[i]))
                return true;
        }
        return false;
    }

}
