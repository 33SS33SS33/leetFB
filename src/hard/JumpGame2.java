package hard;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * Tags: Array, Greedy, DP
 */
class JumpGame2 {
    public static void main(String[] args) {
        int[] A = { 0, 1, 3, 1, 2 };
        System.out.println(new JumpGame2().jump(A));
        System.out.println(new JumpGame2().jumpB(A));
        System.out.println(new JumpGame2().jumpC(A));
    }

    /**
     * I try to change this problem to a BFS problem, where nodes in level i are all the
     * nodes that can be reached in i-1th jump. for example. 2 3 1 1 4 , is 2|| 3 1|| 1 4 ||
     * clearly, the minimum jump of 4 is 2 since 4 is in level 3. my ac code.
     */
    int jumpA(int A[], int n) {
        if (n < 2)
            return 0;
        int level = 0, currentMax = 0, i = 0, nextMax = 0;
        while (currentMax - i + 1 > 0) { //nodes count of current level>0
            level++;
            //traverse current level , and update the max nextMax=max(nextMax,A[i]+i);
            for (; i <= currentMax; i++) {
                if (nextMax >= n - 1)
                    return level; // if last element is in level+1, then the min jump=level
            }
            currentMax = nextMax;
        }
        return 0;
    }

    /**
     * ganker--其实思路和Jump Game还是类似的，只是原来的全局最优现在要分成step步最优和step-1步最优
     * （假设当前步数是step）。当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，
     * 我们就可以更新步数，将step+1。时间复杂度仍然是O(n)，空间复杂度也是O(1)
     */
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
            return -1;
        return step;
    }

    /**
     * 动归
     */
    public int jumpC(int[] A) {
        int[] steps = new int[A.length];

        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        return steps[A.length - 1];
    }

    /**
     * 错的
     * Use last to store how far we already can reach Compare i with last
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

}
