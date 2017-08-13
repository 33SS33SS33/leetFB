package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array such that the difference between nums[i]
 * and nums[j] is at most t and the difference between i and j is at most k.
 * BST
 * 使了滑动窗口还有桶排序 bucket sort
 * 题目中 t是两个元素值的差 k是两个元素索引的差 所以整体思想是创建k个桶 每个桶的范围是t
 * 也就是说 比如[10, 5, 2, 15, 20] k = 3 t = 7
 * 这样就是一直保持3个桶(桶的多少就是窗口的大小) 这样桶里的元素他们索引的差肯定是小于等于k的
 * 桶的编号是直接用当前元素值整除t来决定的  比如10/7 就属于1号桶 5/7 就是0号桶
 * 由于桶的范围就是t 所以如果两个元素桶的编号一样 那一定是符合要求的 剩下还有一种可能就是符合条件的元素只可能在相邻的两个桶  所以需要小循环的if判断
 * 然后每次桶的数量超过了k 就删除离k最远的桶
 * buckets[buckNum] = nums[i] 这句其实就是用来创建新的桶编号并且赋值  一个桶编号的值是不可能被更新的  因为同一个桶编号的值必然负号if的那个判断 直接就被反悔了
 */
public class ContainsDuplicate3 {
    public static void main(String[] args) {
        int[] num = {1, 8, 7, 5, 8};
        System.out.println(containsNearbyAlmostDuplicatea(num, 3, 2));
        System.out.println(containsNearbyAlmostDuplicateB(num, 3, 2));
        System.out.println(containsNearbyAlmostDuplicateC(num, 3, 2));
    }

    /**
     * is easier to understand----better----
     */
    public static boolean containsNearbyAlmostDuplicateC(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;
        SortedSet<Long> set = new TreeSet<Long>();
        for (int j = 0; j < nums.length; j++) {
            long leftBoundary = (long) nums[j] - t;
            long rightBoundary = (long) nums[j] + t + 1;
            SortedSet<Long> subSet = set.subSet(leftBoundary, rightBoundary);
            if (!subSet.isEmpty())
                return true;
            set.add((long) nums[j]);
            if (j >= k) {
                set.remove((long) nums[j - k]);
            }
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicatea(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;
        Map<Long, Long> map = new HashMap<Long, Long>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket) || (map.containsKey(bucket - 1)
                    && remappedNum - map.get(bucket - 1) <= t) || (map.containsKey(bucket + 1)
                    && map.get(bucket + 1) - remappedNum <= t))
                return true;
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicateB(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if ((set.floor(c) != null && c <= set.floor(c) + t) || (set.ceiling(c) != null
                    && c >= set.ceiling(c) - t))
                return true;
            set.add(c);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }

}
