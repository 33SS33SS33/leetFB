package easy;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**Given an array of integers,
 * find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.*/
public class ContainsDuplicate3 {

    public static void main(String[] args) {
        int[] num={1,8,7,5,8};
        System.out.println(containsNearbyAlmostDuplicate(num,3,2));
        System.out.println(containsNearbyAlmostDuplicateB(num,3,2));
        System.out.println(containsNearbyAlmostDuplicateC(num,3,2));
    }


    /** is easier to understand----better----*/
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

    public static boolean containsNearbyAlmostDuplicateB(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;

        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if ((set.floor(c) != null && c <= set.floor(c) + t)
                    || (set.ceiling(c) != null && c >= set.ceiling(c) -t))
                return true;

            set.add(c);

            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0) return false;
        if (nums.length <= 1) return false;

        Tree tree = new Tree();
        tree.add(nums[0]);

        int p = 0;
        for(int i = 1; i < nums.length; i++){
            tree.add(nums[i]);
            if(tree.nearSub(nums[i]) <= t){
                return true;
            }
            if(tree.size > k){
                tree.remove(nums[p++]);
            }
        }
        return false;
    }
    static class Tree {
        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
        int size = 0;
        void add(Integer n){
            Integer v = tree.get(n);
            if(v == null){
                v = 0;
            }
            tree.put(n, v + 1);
            size++;
        }

        void remove(Integer n){
            Integer v = tree.get(n);
            v--;
            if(v == 0){
                tree.remove(n);
            } else {
                tree.put(n, v);
            }
            size--;
        }
        // fuck overflow
        long nearSub(Integer n){
            Integer v = tree.get(n);
            if(v >= 2) return 0;
            long min = Long.MAX_VALUE;
            Integer h = tree.higherKey(n);
            if(h != null){
                min = h - n;
            }
            Integer l = tree.lowerKey(n);
            if(l != null){
                min = Math.min(min, (long)n - l);
            }
            return min;
        }
    }
}
