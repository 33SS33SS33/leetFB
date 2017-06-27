package aFB;

/**
 * Created by krystal on 5/4/17.
 */
public class TotalHammingDistance {
    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(totalHammingDistance(nums));
    }

    //https://discuss.leetcode.com/topic/72092/java-o-n-time-o-1-space
    public static int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int i = 0; i < n; i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount * (n - bitCount);
        }
        return total;
    }
}
