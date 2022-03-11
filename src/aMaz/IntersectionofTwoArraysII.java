package aMaz;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanshan on 16/6/17.
 * Given two arrays, write a function to compute their intersection.
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited
 * such that you cannot load all elements into the memory at once?
 */
public class IntersectionofTwoArraysII {
    public static void main(String[] args) {
        int[] num1 = {1, 2, 2, 1};
        int[] num2 = {2, 2};
        int[] res = intersectionofTwoArraysII(num1, num2);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    /**
     * Given two arrays, write a function to compute their intersection.Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     * Note:Each element in the result should appear as many times as it shows in both arrays.
     * The result can be in any order.
     * Follow up:What if the given array is already sorted? How would you optimize your algorithm?
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     * What if elements of nums2 are stored on disk, and the memory is limited. such that you cannot load all elements into the memory at once?
     */
    public static int[] intersectionofTwoArraysII(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : nums1) {
            if (map.containsKey(j)) map.put(j, map.get(j) + 1);
            else map.put(j, 1);
        }
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) > 0) {
                result.add(j);
                map.put(j, map.get(j) - 1);
            }
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

}
