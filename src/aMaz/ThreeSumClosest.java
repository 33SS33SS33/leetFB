package aMaz;

import java.util.Arrays;

/**
 * Tags: Arrays, Two pointers
 */
class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();
        int[] s = {-1, 0, 1, 2, -1, -4};
        int target = 4;
        System.out.println(threeSumClosest(s, target));
    }

    /**
     * Given an array S of n integers, find three integers in S such that the
     * sum is closest to a given number, target. Return the sum of the three
     * integers. You may assume that each input would have exactly one solution.
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * 使用一个循环 然后加上双指针即可 记得start也要移动 要不结果不全
     */
    public static int threeSumClosest(int[] num, int target) {
        if (num == null || num.length <= 2)
            return Integer.MIN_VALUE;
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

}
