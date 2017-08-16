package medium;

/**
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * Tags: Array, DP
 * (E) House Robber (M) Product of Array Except Self
 * 这道题是maximum subarray sum 的变体
 * 已然用动归 DP来解决 每个dp数组保存的都是以当前元素结尾的乘积最大值
 * 但是要注意因为是相乘 负负得正 所以还要保存最小值 因为最小值碰到个负数很可能就会变成最大值
 */
class MaxProductSubarr {
    public static void main(String[] args) {
        int[] A = {2, 3, -2, 4};
        System.out.println(maxProductA(A));
    }

    /**
     * DP, update according to A[i]  最好的
     * f(k) = Largest product subarray, from index 0 up to k.
     * g(k) = Smallest product subarray, from index 0 up to k.
     * f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
     * g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
     */
    public static int maxProductA(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int max = A[0], min = A[0], maxAns = A[0];
        for (int i = 1; i < A.length; i++) {
            int mx = max, mn = min;
            max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
            min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }

}
