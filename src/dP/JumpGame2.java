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
