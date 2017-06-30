package easy;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/6.
 * Rotate an array of n elements to the right by k steps
 * For example, with n = 7 and k = 3,
 * the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different
 * ways to solve this problem.
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * Related problem:
 * Reverse Words in a String II
 * Tags: Array
 * 重要的一道题 有很多解法
 * 比如重新构造两个字符串 然后拼接 要用到额外的空间 未实现
 * 将两部分翻转 之后再整体翻转 实现
 * 每次向右平移一格 然后平移k次 未实现
 * 最重要 是最后一次提交的解法
 * 就是每次把cur的元素按放在它最终应该在的位置 然后把那个位置的元素先扔到cur的位置(临时存放) 然后再算出来这个元素该在的位置 这样不停的rotate
 * 记录距离的原因是  比如1234 k=2 这样1 3的置换就是一个圈 这时候就需要把idx往前进一下才能去换2 4
 * cur表示当前待被放到正确位置的元素  distance表示现在换了多少距离 idx表示的cur那个元素应该被换到得位置
 */
class RotateArray {
    public static void main(String[] args) {
        RotateArray r = new RotateArray();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        r.rotatea(nums, k);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        r.rotate(nums1, k1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
        int k2 = 3;
        r.rotate2(nums2, k2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1, 2};
        int k3 = 2;
        r.rotate3(nums3, k3);
        System.out.println(Arrays.toString(nums3));
    }

    public void rotatea(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * O(n) Time, O(1) Space
     * Build a full circle of rotation
     * Start from current index and repeat exactly "length of array" times
     * 1. Calculate new index which is current index move k steps forward
     * If move out of range, just start from beginning again
     * newIdx = (curIdx + k ) % len
     * 2. Circle can be the same, for example, n = 6, k = 2
     * Index will be 0, 2, 4, 0, 2, 4
     * So save the start index of the circle
     * If start from there again, move one step forward
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;
        if (nums.length == 1 || k == 0 || k == nums.length)
            return; // special cases
        int len = nums.length;
        k %= len;
        int idx = 0;
        int tmp = nums[idx]; // the number to write to new index
        int tmp2; // save the number at new index
        for (int i = 0, j = 0; i < len; i++) { // j is the start index of current circle
            idx = (idx + k) % len;
            tmp2 = nums[idx];
            nums[idx] = tmp;
            tmp = tmp2;
            if (idx == j) { // circle ends
                idx = ++j; // move to next circle
                tmp = nums[idx];
            }
        }
    }

    /**
     * Bubble Rotate  O(1) space, the time is O(n*k)
     */
    public static void rotate2(int[] arr, int order) {
        if (arr == null || order < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }
        for (int i = 0; i < order; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    /**
     * Intermediate Array, Space is O(n) and time is O(n)
     */
    public void rotate3(int[] nums, int k) {
        if (k > nums.length)
            k = k % nums.length;
        int[] result = new int[nums.length];
        for (int i = 0; i < k; i++) {
            result[i] = nums[nums.length - k + i];
        }
        int j = 0;
        for (int i = k; i < nums.length; i++) {
            result[i] = nums[j];
            j++;
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

}
