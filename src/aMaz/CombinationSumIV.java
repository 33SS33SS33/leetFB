package aMaz;

/**
 * Created by krystal on 5/4/17.
 */
public class CombinationSumIV {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2};
        System.out.println(combinationSum4a(prices, 4));
        System.out.println(combinationSum4(prices, 4));
    }

    // 最好的 递归有重复计算，使用DP避免重复计算   啊啊啊啊啊啊啊！！！
    //https://discuss.leetcode.com/topic/52302/1ms-java-dp-solution-with-detailed-explanation

    /**
     * Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     * nums = [1, 2, 3] target = 4 The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * Note that different sequences are counted as different combinations.
     * Therefore the output is 7.
     */
    public static int combinationSum4a(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
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
