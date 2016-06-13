package easy;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * Tags: Divide and Conquer, Array, Bit Manipulation
 * 还是用那个投票算法 好像用bit也能做 未实现 重要
 */
class MajorityEle {
    public static void main(String[] args) {
        int[] num = { 1, 2, 1, 3, 6, 1, 4, 1, 1 };
        System.out.println(new MajorityEle().majorityElement(num));
        System.out.println(new MajorityEle().majorityElementB(num));
        System.out.println(new MajorityEle().majorityElementC(num));
    }

    /**
     * Go through the array, assign maj ele if count is 0
     * Add 1 to count if same element, otherwise minus 1
     */
    public static int majorityElement(int[] num) {
        int maj = num[0];
        for (int count = 0, i = 0; i < num.length && count <= num.length / 2; i++) {
            if (count == 0) {
                maj = num[i];
                count++;
            } else
                count = num[i] == maj ? count + 1 : count - 1;
        }
        return maj;
    }

    /**
     * Runtime: O(n) — Bit manipulation: We would need 32 iterations, each
     * calculating the number of 1's for the ith bit of all n numbers. Since a
     * majority must exist, therefore, either count of 1's > count of 0's or
     * vice versa (but can never be equal). The majority number’s ith bit must
     * be the one bit that has the greater count.
     */

    /**
     * creek
     * Since the majority always take more than a half space, the middle element is guaranteed to be the majority.
     * Sorting array takes nlog(n). So the time complexity of this solution is nlog(n). Cheers!
     */
    public int majorityElementB(int[] num) {
        if (num.length == 1) {
            return num[0];
        }
        Arrays.sort(num);
        return num[num.length / 2];
    }

    /**
     * creek Naive
     * sort the array first, which takes time of nlog(n).
     * Then scan once to find the longest consecutive substrings.
     */
    public int majorityElementC(int[] num) {
        if (num.length == 1) {
            return num[0];
        }
        Arrays.sort(num);
        int prev = num[0];
        int count = 1;
        for (int i = 1; i < num.length; i++) {
            if (num[i] == prev) {
                count++;
                if (count > num.length / 2)
                    return num[i];
            } else {
                count = 1;
                prev = num[i];
            }
        }
        return 0;
    }
}