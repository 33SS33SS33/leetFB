package aMaz;

class MaximumSubarray {
    public static void main(String[] args) {
        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maximumSubarray(A));
    }

    /**
     * Find the contiguous subarray within an array (containing at least one（median）
     * number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     * More practice:
     * If you have figured out the O(n) solution, try coding another solution using
     * the divide and conquer approach, which is more subtle.
     * Tags: Divide and Conquer, Array, DP
     * <p>
     * DP, O(n) Time, O(1) Space
     * If A[i] < 0 && currentMax + A[i] < 0, should recalculate max
     * If A[i] < 0 && currentMax + A[i] >= 0, continue
     * currentMax = max(currentMax + A[i], A[i])
     * maxSubArr = max(currentMax, maxSubArr)
     */
    public static int maximumSubarray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int curMax = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) { // note that i starts from 1
            curMax = Math.max(curMax + A[i], A[i]);
            max = Math.max(curMax, max);
        }
        return max;
    }


    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int n = A.length;
        int max = 1;
        int res = 0;

        int length = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
//            max = Math.max(max, length);
            if (length > max) {
                res = i - 1;
                max = length;
            }
        }

        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
            } else {
                length = 1;
            }
            max = Math.max(max, length);
            if (length > max) {
                res = i;
                max = length;
            }
        }
        return res;
    }

}
