package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * Note:
 * You may assume that you have an infinite number of each kind of coin."
 * "使用dp解
 * 通项公式为
 * res[i] = min([res[i-j] for j in coins if i-j>=0]) + 1
 * 就是要从0到amount 挨个算  当前的这个数量一定是之前的某一个数量然后加上了一个coin里的数量得来的 然后就是取最小的即可
 * 注意一下初始值的设置  设置的是一个不可能得数从而才能知道什么时候返回-1"
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new CoinChange().coinChange(coins, 11));
        System.out.println(new CoinChange().coinChangeB(coins, 11));
    }

    /**
     * Recursive Method:
     * The idea is very classic dynamic programming: think of the last step we take. Suppose we have already found out the best way to sum up to amount a,
     * then for the last step, we can choose any coin type which gives us a remainder r where r = a-coins[i] for all i's. For every remainder,
     * go through exactly the same process as before until either the remainder is 0 or less than 0 (meaning not a valid solution). With this idea,
     * the only remaining detail is to store the minimum number of coins needed to sum up to r so that we don't need to recompute it over and over again.
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return helper(coins, amount, new int[amount]);
    }

    //rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
    private int helper(int[] coins, int rem, int[] count) {
        if (rem < 0)
            return -1; // not valid
        if (rem == 0)
            return 0; // completed
        if (count[rem - 1] != 0)
            return count[rem - 1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * Iterative Method:
     * For the iterative solution, we think in bottom-up manner.
     * Suppose we have already computed all the minimum counts up to sum, what would be the minimum count for sum+1
     */
    public int coinChangeB(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount + 1];
        int sum = 0;
        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

}
