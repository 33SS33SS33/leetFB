package dP;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * <p/>
 * Each element in the array represents your maximum jump length at that
 * position.
 * <p/>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p/>
 * For example:
 * Given array A = [2,3,1,1,4]
 * <p/>
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * <p/>
 * Tags: Array, Greedy, DP
 */
class JumpGame2 {
    public static void main(String[] args) {
        int[] A = { 1, 1, 3, 1, 2 };
        System.out.println(new JumpGame2().jump(A));
        System.out.println(new JumpGame2().jumpB(A));
    }

    /**
     * Use last to store how far we already can reach
     * Compare i with last
     * If we run out of it, update and add 1 more step to result
     * Return if last is already bigger than or equal to the length
     * Use cur to store how far we can reach for the next step
     */
    public int jump(int[] A) {
        int step = 0;
        int last = 0; // how far we already can reach
        int cur = 0; // how far can we reach for next step
        for (int i = 0; i < A.length; i++) {
            if (i > last) { // run out of we can reach, need one more step
                last = cur;
                step++;
                if (last >= A.length)
                    return step;
            }
            cur = Math.max(cur, i + A[i]);
        }
        return step;
    }

    /*ganker--
     * 其实思路和Jump Game还是类似的，只是原来的全局最优现在要分成step步最优和step-1步最优
     * （假设当前步数是step）。当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，
     * 我们就可以更新步数，将step+1。时间复杂度仍然是O(n)，空间复杂度也是O(1)*/
    public int jumpB(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int lastReach = 0;
        int reach = 0;
        int step = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            if (i > lastReach) {
                step++;
                lastReach = reach;
            }
            reach = Math.max(reach, A[i] + i);
        }
        if (reach < A.length - 1)
            return 0;
        return step;
    }
}
