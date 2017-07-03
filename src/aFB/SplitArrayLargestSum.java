package aFB;

/**
 * Created by krystal on 5/4/17.
 */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        System.out.println(SplitArrayLargestSum(nums, 2));
    }

    //https://discuss.leetcode.com/category/537/split-array-largest-sum
    public static int SplitArrayLargestSum(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int) sum;
        //binary search
        long l = max;
        long r = sum;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    public static boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }

}
