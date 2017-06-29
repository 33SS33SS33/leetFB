package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 注意题目要求的sum>=s
 * O(N)
 * 使用滑动窗口 就是如果sum小于s 就移动end向后 如果sum大于等于s 就然后就进另一个循环 每次将左边的往右移动 然后更新最小长度 直到sum小于s 或者 start>end
 * start最多移动到end+1的位置就会跳出小循环 因为 start==end的时候 sum就已经为0了
 * 第二次看了思路 滑动窗口要记住  第一次写的比较简洁
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum v = new MinimumSizeSubarraySum();
        int s = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};

        System.out.println(v.minSubArrayLen(s, nums));
        System.out.println(v.solveN(s, nums));
        System.out.println(v.solveNLogN(s, nums));
    }

    //清晰的
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int st = 0;
        int c = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= s) {
                while (sum - nums[st] >= s) {
                    sum -= nums[st++];
                }
                c = Math.min(c, i - st + 1);
            }
        }
        if (c > nums.length) {
            return 0;
        }
        return c;
    }

    /**
     * 最好的
     */
    private int solveN(int s, int[] nums) {
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (end < nums.length && sum < s)
                sum += nums[end++];
            if (sum < s)
                break;
            while (start < end && sum >= s)
                sum -= nums[start++];
            if (end - start + 1 < minLen)
                minLen = end - start + 1;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++)
            sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length)
                break;
            if (end - i < minLen)
                minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sums[mid] >= key) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}
