package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/4/8.
 */

/**
* Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
* For example,
* Given nums = [0, 1, 3] return 2.
* Note:
* Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*
* Tags: Array, Math, Bit Manipulation
* Similar Problems: (H) First Missing Positive, (M) Single Number, (H) Find the Duplicate Number
*/
public class MissingNumber {
    public static void main(String[] args) {
        int[] A = {1,2,0};
        System.out.println(new MissingNumber().missingNumber1(A));
        System.out.println(new MissingNumber().missingNumber2(A));
        System.out.println(new MissingNumber().missingNumber3(A));
    }
    /**
     * Math   不好
     * Considering that the n numbers are distinct, we can get the sum of the array
     * Then substract it from the sum of 0 ~ n
     */
    public int missingNumber1(int[] nums) {
        int res = nums.length * (nums.length + 1) / 2; // may overflow
        for (int n : nums)
            res -= n;
        return res;
    }

    /**
     * avoid overflow
     */
    public int missingNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += i + 1 - nums[i];
        }
        return res;
    }

    /**
     * Bit Manipulation
     * xor all numbers in the array and from 0 to n, the result is the missing number
     */
    public int missingNumber3(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor = xor ^ nums[i] ^ (i + 1);
        }
        return xor;
    }
}
