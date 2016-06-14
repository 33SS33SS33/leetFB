package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have
 * security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * <p/>
 * dp[i] = max(dp[i-2]+nums[i], dp[i-1])
 * 注意一下初始化条件 dp[0], dp[1] = nums[0], max(nums[0], nums[1])
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] num = { 5, 4, 6, 7 };
        System.out.println(new HouseRobber().roba(num));
        System.out.println(new HouseRobber().rob(num));
    }

    /**
     * dp[i][1] means we rob the current house and dp[i][0] means we don't,
     */
    public int roba(int[] num) {
        int[][] dp = new int[num.length + 1][2];
        for (int i = 1; i <= num.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = num[i - 1] + dp[i - 1][0];
        }
        return Math.max(dp[num.length][0], dp[num.length][1]);
    }

    /**上面的简化为*/
    public int roba2(int[] num) {
        int prevNo = 0;
        int prevYes = 0;
        for (int n : num) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = n + temp;
        }
        return Math.max(prevNo, prevYes);
    }

    public int rob(int[] num) {
        if (num.length == 0)
            return 0;
        if (num.length == 1)
            return num[0];
        int[] P = new int[num.length];
        P[0] = num[0];
        P[1] = Math.max(num[0], num[1]);
        for (int i = 2; i < num.length; i++) {
            P[i] = Math.max(num[i] + P[i - 2], P[i - 1]);
        }
        return P[num.length - 1];
    }

    /**
     * creek DP
     * dp[i] = Math.max(dp[i-1], dp[i-2]+num[i-1]).
     */
    public int robA(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int n = num.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = num[0];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + num[i - 1]);
        }
        return dp[n];
    }

    /**
     * creek We can use two variables, even and odd, to track the maximum value so far as iterating the array
     */
    public int robB(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int even = 0;
        int odd = 0;
        for (int i = 0; i < num.length; i++) {
            if (i % 2 == 0) {
                even += num[i];
                even = even > odd ? even : odd;
            } else {
                odd += num[i];
                odd = even > odd ? even : odd;
            }
        }
        return even > odd ? even : odd;
    }
}
