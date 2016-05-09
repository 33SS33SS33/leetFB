package sum;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the
 * sum is closest to a given number, target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Tags: Arrays, Two pointers
 */

/**使用一个循环 然后加上双指针即可 记得start也要移动 要不结果不全*/
class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();
        int[] s = { -1, 0, 1, 2, -1, -4 };
        int target = 4;
        System.out.println(threeSumClosest(s, target));
        System.out.print(threeSumClosestB(s, target));
    }

    /**
     * Sort and initialize min
     * use two pointers to manipulate sums
     * update min when closer
     * return when min equals to target or all done
     */
    public static int threeSumClosest(int[] num, int target) {
        int closest = 0;
        if (num == null)
            return closest;
        Arrays.sort(num);
        for (int i = 0; i < num.length && i < 3; i++)
            closest += num[i];
        if (num.length < 3)
            return closest;
        for (int i = 0; i < num.length - 2; i++) {
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                if (Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum; // sum is even closer
                    if (closest == target)
                        return closest; // sum == target
                }
                if (sum > target)
                    k--;
                else
                    j++;
            }
        }
        return closest;
    }

    /*这道题跟3Sum很类似，区别就是要维护一个最小的diff，求出和目标最近的三个和。
     * brute force时间复杂度为O(n^3)，
     * 优化的解法是使用排序之后夹逼的方法，总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n)*/
    public static int threeSumClosestB(int[] num, int target) {
        if (num == null || num.length <= 2)
            return Integer.MIN_VALUE;
        Arrays.sort(num);
        int closest = num[0] + num[1] + num[2] - target;
        for (int i = 0; i < num.length - 2; i++) {
            int cur = twoSum(num, target - num[i], i + 1);
            if (Math.abs(cur) < Math.abs(closest))
                closest = cur;
        }
        return target + closest;
    }
    private static int twoSum(int[] num, int target, int start) {
        int closest = num[start] + num[start + 1] - target;
        int l = start;
        int r = num.length - 1;
        while (l < r) {
            if (num[l] + num[r] == target)
                return 0;
            int diff = num[l] + num[r] - target;
            if (Math.abs(diff) < Math.abs(closest))
                closest = diff;
            if (num[l] + num[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return closest;
    }
}
