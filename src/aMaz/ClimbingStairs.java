package aMaz;


class ClimbingStairs {
    public static void main(String[] args) {
//        System.out.println(climbStairs(44));
        System.out.println(climbStairs(44));
    }

    // https://discuss.leetcode.com/topic/5371/basically-it-s-a-fibonacci/5
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     * Input: 2 Output: 2 Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * <p>
     * Input: 3 Output: 3 Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     */
    public static int climbStairs(int n) {
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
/*    public static int climbStairs(int n) {
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
    }*/

}