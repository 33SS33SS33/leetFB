package dP;

/**
 * Created by GAOSHANSHAN835 on 2015/12/31.
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[]  s1 = {1,2,5,1,-1};
        System.out.println(new MaxSubArray().maxSubArray2(s1));
        System.out.println(new MaxSubArray().maxSubArray(s1));
    }
    /**3.8遇到*/
    public int maxSubArray2(int[] A) {
        int maxEndingHere = A[0], maxSoFar = A[0];
        for (int i = 1; i < A.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

    /**S1*/
    public int maxSubArray(int[] A) {
        return maxSubArrayHelper(A, 0, A.length - 1);
    }
    private int maxSubArrayHelper(int[] A, int L, int R) {
        if (L > R) return Integer.MIN_VALUE;
        int M = (L + R) / 2;
        int leftAns = maxSubArrayHelper(A, L, M - 1);
        int rightAns = maxSubArrayHelper(A, M + 1, R);
        int lMaxSum = 0;
        int sum = 0;
        for (int i = M - 1; i >= L; i--) {
            sum += A[i];
            lMaxSum = Math.max(sum, lMaxSum);
        }
        int rMaxSum = 0;
        sum = 0;
        for (int i = M + 1; i <= R; i++) {
            sum += A[i];
            rMaxSum = Math.max(sum, rMaxSum);
        }
        return Math.max(lMaxSum + A[M] + rMaxSum, Math.max(leftAns, rightAns));
    }
}
