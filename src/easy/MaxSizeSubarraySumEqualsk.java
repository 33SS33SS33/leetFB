package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */

/**
 * "Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * Follow Up:
 * Can you do it in O(n) time?"
 * "需要用到哈希表 遍历一遍数组 首先存入到当前index为止 所有的和
 * 然后查一下有没有当前这个和-k存储在于哈希表内 若有就计算一下
 * 注意一下哈希表最开始存的0 是考虑了如果当前的和直接等于k的情况"
 */
public class MaxSizeSubarraySumEqualsk {
}
