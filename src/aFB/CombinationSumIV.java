package aFB;

/**
 * Created by krystal on 5/4/17.
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 */
public class CombinationSumIV {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2};
        System.out.println(combinationSum4(prices, 4));
    }

    public static int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }
}
