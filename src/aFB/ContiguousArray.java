package aFB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by krystal on 5/4/17.
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Similar idea to solve LC 325 : Maximum Size Subarray Sum Equals k
 */

public class ContiguousArray {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2};
        System.out.println(findMaxLength(prices));
    }

    public static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum));
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return max;
    }

}
