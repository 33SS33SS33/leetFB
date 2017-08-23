package medium;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * Tags: Array, Math, Bit Manipulation
 * Similar Problems: (H) First Missing Positive, (M) Single Number, (H) Find the Duplicate Number
 * 首先 可以用等差数列之和减去当前数组之和就可以 未实现
 * 还可以用位操作 异或
 * 思路是 这个数组其实就是0到n的数列里少了一个数字 那么如果把这个数组和一个0到n得完全的数列并起来
 * 那么这个大的数组里所有的数字都出现了两次 只有这个少的数字出现了一次 所以从头到尾异或一次就是结果
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] A = {1, 2, 0};
        System.out.println(new MissingNumber().missingNumber2(A));
        System.out.println(new MissingNumber().missingNumber3(A));
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


}
