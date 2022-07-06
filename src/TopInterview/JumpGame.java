package TopInterview;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jumpGame2 length at that position.
 * Determine if you are able to reach the last index.
 * A = [2,3,1,1,4], return true. A = [3,2,1,0,4], return false.
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = {3, 2, 1, 0, 4};
        int[] B = {2, 3, 1, 1, 4};
        int[] C = {0};
        int[] D = {2, 5, 0, 0};
        System.out.println(j.jumpGame(A));
//        System.out.println(j.jumpGameb(B));
        System.out.println(j.jumpGame(C));
//        System.out.println(j.jumpGameb(D));
    }

    //backtracking
    public boolean jumpGame(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    //贪心算法
/*
    public boolean canJumpb(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
*/

/*    public boolean jumpGame(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > max) {    //?????
                return false;
            }
            max = Math.max(A[i] + i, max);
        }
        return true;
    }*/

    /**
     * 时间复杂度是O(n)，而空间上是O(1)
     * The key to solve this problem is to find:
     * 1) when the current position can not reach next position (return false) ,
     * 2) when the maximum index can reach the end (return true).
     * The largest index that can be reached is: i + A[i].
     */
/*    public boolean jumpGameb(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int reach = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            reach = Math.max(A[i] + i, reach);
        }
        if (reach < A.length - 1)
            return false;
        return true;
    }*/
}
