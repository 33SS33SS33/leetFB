package dP;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = { 3, 2, 1, 0, 4 };
        int[] B = { 2, 3, 1, 1, 4 };
        int[] C = { 0 };
        int[] D = { 2, 5, 0, 0 };
        System.out.println(j.canJump(A));
        System.out.println(j.canJumpB(A));
        System.out.println(j.canJumpC(A));
        System.out.println(j.canJumpD(A));
        System.out.println(j.canJump(B));
        System.out.println(j.canJumpB(B));
        System.out.println(j.canJump(C));
        System.out.println(j.canJumpB(C));
        System.out.println(j.canJump(D));
        System.out.println(j.canJumpB(D));
        System.out.println(j.canJumpC(D));
        System.out.println(j.canJumpD(D));
    }

    /**
     * Dynamic Programming
     * Keep track of the maximum of jumps we left
     * Initialized as A[0]
     * Traverse from second to second last
     * Reduce 1 every time we jump
     * maxJump should be max of maxJump - 1 and A[i]
     * if maxJump reduces to zero, we are not able to reach anymore
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0)
            return false;
        if (A.length == 1)
            return true; // already reach last index
        if (A[0] == 0)
            return false; // note its important cause we start from 1
        int maxJump = A[0];
        for (int i = 1; i < A.length - 1; i++) {
            maxJump = Math.max(maxJump - 1, A[i]);
            if (maxJump == 0)
                return false;
        }
        return true;
    }

    /**时间复杂度是O(n)，而空间上是O(1)*/
    public boolean canJumpC(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int reach = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            reach = Math.max(A[i] + i, reach);
        }
        if (reach < A.length - 1)
            return false;
        return true;
    }

    public boolean canJumpD(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(A[i] + i, max);
        }
        return true;
    }

    public boolean canJumpE(int A[], int n) {
        int last = n - 1, i;
        for (i = n - 2; i >= 0; i--) {
            if (i + A[i] >= last)
                last = i;
        }
        return last <= 0;
    }
    /**
     * creek
     * The key to solve this problem is to find:
     * 1) when the current position can not reach next position (return false) ,
     * and 2) when the maximum index can reach the end (return true).
     * The largest index that can be reached is: i + A[i].
     */
    public boolean canJumpB(int[] A) {
        if (A.length <= 1)
            return true;
        int max = A[0]; //max stands for the largest index that can be reached.
        for (int i = 0; i < A.length; i++) {
            //if not enough to go to next
            if (max <= i && A[i] == 0)
                return false;
            if (i + A[i] > max) {  //update max
                max = i + A[i];
            }
            if (max >= A.length - 1)  //max is enough to reach the end
                return true;
        }
        return false;
    }
}
