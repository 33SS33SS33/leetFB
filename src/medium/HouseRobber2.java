package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * After robbing those houses on that street, the thief has found himself a new place for his thievery
 * so that he will not get too much attention.
 * This time,all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * DP 动态规划  很经典
 * 动态规划推算转移方程的时候 当前的D(i)表示的是当前状态的最优解
 * 而且推算的时候不要只考虑D(i-1)的状态 也要考虑 D(i-2)…之类的状态
 * 递推公式dp[i] = max(num[i] + dp[i - 2], dp[i - 1]) dp[i]表示到i位置时不相邻数能形成的最大和
 * 这道题因为有环的存在  所以可以先只去掉最开始的节点 然后算一下最大值 然后再只去掉最后一个节点 再算一下最大值 然后这两个结果取最大值即可
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {2, 4, 1, 6};
        System.out.println(roba(nums));
    }

    //https://discuss.leetcode.com/topic/14375/simple-ac-solution-in-java-in-o-n-with-explanation
    public static int roba(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(roba(nums, 0, nums.length - 2), roba(nums, 1, nums.length - 1));
    }

    private static int roba(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }

}
