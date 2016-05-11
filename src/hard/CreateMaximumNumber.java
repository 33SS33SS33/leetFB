package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */
/**
 * "Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]"
 */
/**"问题可以转化为这样的两个子问题：
 1. 从数组nums中挑选出t个数，在保持元素相对顺序不变的情况下，使得选出的子数组最大化。
 2. 在保持元素相对顺序不变的前提下，将数组nums1与数组nums2合并，使合并后的数组最大化。
 Prep是用来解决第1个子问题  merge用来解决第2个子问题 解法可以看第二个链接
 python是可以直接比较两个数组的大小的 "

 "https://leetcode.com/discuss/75756/share-my-greedy-solution
 http://algobox.org/2015/12/24/create-maximum-number/"*/
public class CreateMaximumNumber {
}
