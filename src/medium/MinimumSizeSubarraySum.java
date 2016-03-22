package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p/>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum v = new MinimumSizeSubarraySum();
        int s = 7;
        int[] nums = { 2, 3, 1, 2, 4, 3 };

        System.out.println(v.minSubArrayLen(s, nums));
        System.out.println(v.minSubArrayLenB(s, nums));
    }

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

    public int minSubArrayLenB(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // initialize min length to be the input array length
        int result = nums.length;
        int i = 0;
        int sum = nums[0];
        for (int j = 0; j < nums.length; ) {
            if (i == j) {
                if (nums[i] >= s) {
                    return 1; //if single elem is large enough
                } else {
                    j++;
                    if (j < nums.length) {
                        sum = sum + nums[j];
                    } else {
                        return result;
                    }
                }
            } else {
                //if sum is large enough, move left cursor
                if (sum >= s) {
                    result = Math.min(j - i + 1, result);
                    sum = sum - nums[i];
                    i++;
                    //if sum is not large enough, move right cursor
                } else {
                    j++;
                    if (j < nums.length) {
                        sum = sum + nums[j];
                    } else {
                        if (i == 0) {
                            return 0;
                        } else {
                            return result;
                        }
                    }
                }
            }
        }
        return result;
    }

}
