package TopInterview;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/6.
 * Rotate an array of n elements to the right by k steps
 * For example, with n = 7 and k = 3,
 * the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note: Try to come up as many solutions as you can, there are at least 3 different
 * ways to solve this problem.
 * Hint: Could you do it in-place with O(1) extra space?
 * Related problem:
 * Reverse Words in a String II
 * 重要的一道题 有很多解法
 * 比如重新构造两个字符串 然后拼接 要用到额外的空间 未实现
 * 将两部分翻转 之后再整体翻转 实现
 * 每次向右平移一格 然后平移k次 未实现
 * 最重要 是最后一次提交的解法
 * 就是每次把cur的元素按放在它最终应该在的位置 然后把那个位置的元素先扔到cur的位置(临时存放)
 * 然后再算出来这个元素该在的位置 这样不停的rotate
 * 记录距离的原因是  比如1234 k=2 这样1 3的置换就是一个圈 这时候就需要把idx往前进一下才能去换2 4
 * cur表示当前待被放到正确位置的元素  distance表示现在换了多少距离 idx表示的cur那个元素应该被换到得位置
 */
class RotateArray {
    public static void main(String[] args) {
        RotateArray r = new RotateArray();
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        r.rotateArray(nums1, k1);
        System.out.println(Arrays.toString(nums1));
    }

    //time is O(n),Space is O(1)
    public void rotateArray(int[] nums, int k) {
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

}
