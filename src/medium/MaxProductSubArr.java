package medium;

/**
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest product.
 * <p/>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * <p/>
 * Tags: Array, DP
 */
class MaxProductSubArr {

    public static void main(String[] args) {
        int[] A = { 2, 3, -2, 4 };

        System.out.println(maxProduct(A));
        System.out.println(maxProduct2(A));
        System.out.println(maxProductB(A));
    }

    /**
     * DP, update according to A[i]
     * f(k) = Largest product subarray, from index 0 up to k.
     * g(k) = Smallest product subarray, from index 0 up to k.
     * <p/>
     * f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
     * g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
     */
    public static int maxProduct(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int max = A[0], min = A[0], res = A[0];
        for (int i = 1; i < A.length; i++) {
            int preMax = max, preMin = min; // results of last loop
            max = Math.max(Math.max(A[i], preMax * A[i]), preMin * A[i]);
            min = Math.min(Math.min(A[i], preMax * A[i]), preMin * A[i]);
            res = Math.max(max, res);
        }
        return res;
    }

    /**
     * DP
     */
    public static int maxProduct2(int[] A) {
        assert A.length > 0;
        int max = A[0], min = A[0], maxAns = A[0];
        for (int i = 1; i < A.length; i++) {
            int mx = max, mn = min;
            max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
            min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }

    /**
     * Brute-force
     */
    public static int maxProductB(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int l = 0; l < A.length; l++) {
                if (i + l < A.length) {
                    int product = calProduct(A, i, l);
                    max = Math.max(product, max);
                }
            }
        }
        return max;
    }

    public static int calProduct(int[] A, int i, int j) {
        int result = 1;
        for (int m = i; m <= j; m++) {
            result = result * A[m];
        }
        return result;
    }
}
