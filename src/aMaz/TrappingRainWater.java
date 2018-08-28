package aMaz;

/**
 * HARD
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * For example,
 * Given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
 * Tags: Array, Stack, Two pointers
 * 对任意位置i，在i上的积水，不包括i本身, 由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。
 * 定义Hmin = min(A[left], A[right])，则积水量Si为：
 * Hmin <= A[i]时，Si = 0
 * Hmin > A[i]时，Si = Hmin - A[i]
 * 所以第一遍扫描  记录的是当前索引i最左边的最高的bar 然后第二遍从后往前
 * 首先先得到当前i最右边最高的bar 然后这两个最高的最小值减去当前的高度就是水量
 */
class TrappingRainWater {
    public static void main(String[] args) {
        int[] A = {10, 2, 20};
        int[] B = {5, 4, 3, 2, 8};
        TrappingRainWater m = new TrappingRainWater();
        System.out.println(m.trap(A));
        System.out.println(m.trap(B));
    }

    /**
     * https://leetcode.com/problems/trapping-rain-water/solution/
     * Using 2 pointers
     * Time complexity: O(n)O(n). Single iteration of O(n)O(n).
     * Space complexity: O(1)O(1) extra space. Only constant space required for \text{left}left, \text{right}right, left_max and right_max.
     * Keep track of the maximum height from both forward directions backward 最好的
     * directions, call them leftmax and rightmax
     */
    public int trap(int[] A) {
        int a = 0;
        int b = A.length - 1;
        int max = 0;
        int leftmax = 0;
        int rightmax = 0;
        while (a <= b) {
            leftmax = Math.max(leftmax, A[a]);
            rightmax = Math.max(rightmax, A[b]);
            if (leftmax < rightmax) {
                max += (leftmax - A[a]); // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            } else {
                max += (rightmax - A[b]);
                b--;
            }
        }
        return max;
    }

}