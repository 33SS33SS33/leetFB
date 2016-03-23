package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = { 2, 4, 1, 6 };
        System.out.println(robA(nums));
        System.out.println(robB(nums));
    }

    public static int robA(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length - 1));
    }
    // rob1
    static int rob(int[] num, int st, int len) {
        if (len == 0)
            return 0;
        if (len == 1)
            return num[st + 0];
        int[] P = new int[len];
        P[0] = num[st + 0];
        P[1] = Math.max(num[st + 0], num[st + 1]);
        for (int i = 2; i < len; i++) {
            P[i] = Math.max(num[st + i] + P[i - 2], P[i - 1]);
        }
        return P[len - 1];
    }

    /**
     * creek
     * There are two cases here 1) 1st element is included and last is not included
     * 2) 1st is not included and last is included.
     * Therefore, we can use the similar dynamic programming approach to scan the array twice and get the larger value.
     */
    public static int robB(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[1], nums[0]);
        }
        //include 1st element, and not last element
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        //not include frist element, and include last element
        int[] dr = new int[n + 1];
        dr[0] = 0;
        dr[1] = nums[1];
        for (int i = 2; i < n; i++) {
            dr[i] = Math.max(dr[i - 1], dr[i - 2] + nums[i]);
        }
        return Math.max(dp[n - 1], dr[n - 1]);
    }
}
