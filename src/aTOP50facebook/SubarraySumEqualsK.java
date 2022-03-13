package aTOP50facebook;

import java.util.HashMap;
import java.util.Map;

//TODO
public class SubarraySumEqualsK {
    public int subarraySumEqualsK(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int num : nums) {
            sum += num;
            result += preSum.getOrDefault(sum - k, 0);
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
