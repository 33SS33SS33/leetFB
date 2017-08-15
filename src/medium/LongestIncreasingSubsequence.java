package medium;

import java.util.Arrays;

/**
 * Created by shanshan on 16/5/9.
 * "Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?"
 * "首先n^2的算法可以用dp 方程是
 * dp[x] = max(dp[x], dp[y] + 1) 其中 y < x 并且 nums[x] > nums[y]
 * nlgn的算法在geekforgeek里 大体思路是这样
 * 当我们插入一个元素进数组 这样对于我们当前的那个最长的递增子序列 有三种情况
 * 第一 当前元素比最长的序列的任何元素都小 那么这个元素就是成为下一个递增序列的第一个
 * 第二 当前元素如果比最长序列的最后一个元素还大 那么他就是这个最长序列的下一个元素
 * 第三 如果这个元素在最长序列的范围  那么就要知道这个元素比哪些元素小 才能知道它插入的位置
 * 所以  我们保存一个数组res 这个数组的第i个元素  表示的是长度为i+1的递增子序列的最后一个元素的值
 * 这样当遇到一个新元素 我们就可以二分查找 然后更新对应长度的值 如果他比所有元素都大 那么就插入进数组
 * 这样说明最大长度新增了一个
 * 最后的返回值则是数组res的长度
 * 这里要注意二分查找的写法  返回的位置应该是插入当前元素到这个元素之前"
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if (i < 0)
                i = -(i + 1);
            dp[i] = x;
            if (i == len)
                len++;
        }
        return len;
    }

}
