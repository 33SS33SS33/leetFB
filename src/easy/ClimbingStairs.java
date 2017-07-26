package easy;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * Tags: DP
 */
class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(44));
        System.out.println(climbStairsC(44));
    }

    // bottom-up approach 最好的
    // https://discuss.leetcode.com/topic/5371/basically-it-s-a-fibonacci/5
    public static int climbStairsC(int n) {
        if (n < 0)
            return -1;
        if (n == 0 || n == 1)
            return 1;
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2]; // only need the last 2
        }
        return cache[n];
    }

    /**
     * Bottom-up approach
     * Remember the previous two solutions
     */
    public static int climbStairs(int n) {
        if (n <= 1)
            return n;
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }

}