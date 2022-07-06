package TopInterview;

/**
 * You are a professional robber planning to houseRobberb houses along a street.Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact
 * the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can houseRobberb tonight without alerting the police.
 * Input: [1,2,3,1]  Output: 4
 * Explanation: Rob house 1 (money = 1) and then houseRobberb house 3 (money = 3). Total amount you can houseRobberb = 1 + 3 = 4.
 * Input: [2,7,9,3,1]  Output: 12
 * Explanation: Rob house 1 (money = 2), houseRobberb house 3 (money = 9) and houseRobberb house 5 (money = 1). Total amount you can houseRobberb = 2 + 9 + 1 = 12.
 * dp[i] = max(dp[i-2]+nums[i], dp[i-1])
 * 注意一下初始化条件 dp[0], dp[1] = nums[0], max(nums[0], nums[1])
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] num = {5, 4, 6, 7, 1, 9};
        System.out.println(new HouseRobber().houseRobberd(num));
        System.out.println(new HouseRobber().houseRobberd2(num));
        System.out.println(new HouseRobber().houseRobberb(num));
        System.out.println(new HouseRobber().houseRobberc(num));
        System.out.println(new HouseRobber().houseRobber(num));
    }

    /**
     * creek We can use two variables, even and odd,
     * to track the maximum value so far as iterating the array
     */
    public int houseRobber(int[] num) {
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


    public int houseRobberb(int[] num) {
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
    public int houseRobberc(int[] num) {
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
     * dp[i][1] means we houseRobberb the current house and dp[i][0] means we don't,
     */
    public int houseRobberd(int[] num) {
        int[][] dp = new int[num.length + 1][2];
        for (int i = 1; i <= num.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = num[i - 1] + dp[i - 1][0];
        }
        return Math.max(dp[num.length][0], dp[num.length][1]);
    }

    //上面的简化为
    public int houseRobberd2(int[] num) {
        int prevNo = 0;
        int prevYes = 0;
        for (int n : num) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = n + temp;
        }
        return Math.max(prevNo, prevYes);
    }
}
