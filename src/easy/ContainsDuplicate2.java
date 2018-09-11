package easy;

import java.util.HashSet;
import java.util.Set;

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
     * It iterates over the array using a sliding window. The front of the window is at i, the rear of the window is k steps back.
     * The elements within that window are maintained using a Set. While adding new element to the set, if add() returns false,
     * it means the element already exists in the set. At that point, we return true. If the control reaches out of for loop,
     * it means that inner return true never executed, meaning no such duplicate element was found.
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
