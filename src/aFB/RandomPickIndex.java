package aFB;

import java.util.Random;

/**
 * Created by krystal on 5/4/17.
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must wordSearchb in the array.
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * Example:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */
public class RandomPickIndex {
    int[] nums;
    Random rnd;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }

    public int pick(int target) {
        int result = -1;
        // to record how many targets in the array
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
            /**？？？啊啊啊
             For the nth target, ++count is n. Then the probability that rnd.nextInt(++count)==0 is 1/n.
             Thus, the probability that return nth target is 1/n.
             For (n-1)th target, the probability of returning it is (n-1)/n * 1/(n-1)= 1/n.
             */
            if (rnd.nextInt(++count) == 0)
                result = i;
        }

        return result;
    }
}
