package aMaz;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/4/8.
 */
public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
//        int res = ps.perfectSquaresb(13);
//        System.out.println("res: " + res);
        int res = ps.perfectSquares(13);
        System.out.println("res: " + res);
    }

    /**
     * Given a positive integer n, find the least number of perfect square numbers
     * (for example, 1, 4, 9, 16, ...) which sum to n.
     * given n = 12, return 3 because 12 = 4 + 4 + 4;
     * given n = 13, return 2 because 13 = 4 + 9
     * 可以用 动态规划 DP 来做
     * 通项公式是 dp[i] = 1 + min (dp[i-j*j] for j*j<=i)
     * 就是看看当前数去掉一个完全平方数 然后找最小值  然后+1(1就是代表这个去掉的完全平方数)
     * DP, bottom-up
     * given n = 12, return 3 because 12 = 4 + 4 + 4;
     */
    public int perfectSquares(int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                res[i] = Math.min(res[i], res[i - j * j] + 1);
            }
        }
        return res[n];
    }

/*    public int perfectSquaresb(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while (i - j * j >= 0) {
                min = Math.min(min, dp[i - j * j] + 1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }*/

}
