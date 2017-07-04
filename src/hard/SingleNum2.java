package hard;

/**
 * Given an array of integers, every element appears three times except for
 * one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * Tags: Bit Manipulation
 * 右边的两个连接是这类题的通用解法 https://leetcode.com/discuss/56524/a-general-c-solution-for-these-type-problems
 * https://leetcode.com/discuss/54970/an-general-way-to-handle-all-this-sort-of-questions
 * 如果所有元素都是连续出现了三次 那么把所有这些数字按位加起来 那么每一位都应该是三的倍数 那么除以3余数是0
 * 如果这时候有个数只有一次 那么除以3会有余数 所以把这些所有的数按位加起来 然后按位模3 之后的数就是多出来的数
 * 注意还要考虑负数的情况 如果是负数 必须这样相减(res是31位 1<<31是32位) 才能出来负数
 * 貌似是把符号位给减出来才行 因为符号位第32位不够减 所以才出来了负号
 * python语言自己的问题
 */
class SingleNum2 {
    public static void main(String[] args) {
        int[] A = {1, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7};
        System.out.println(singleNum(A));
        System.out.println(singleNumberB(A));
        System.out.println(singleNumberC(A));
    }

    /**
     * Use ones to store those nums only appeared once 最好的
     * twos to store those nums appeared twice
     */
    public static int singleNum(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            ones = (ones ^ A[i]) & ~twos; // in ones not in twos
            twos = (twos ^ A[i]) & ~ones; // in twos not in ones
        }
        return ones; // only appeared once
    }

    public static int singleNumberB(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    /**
     * 时间复杂度是O(n)。而空间复杂度需要一个32个元素的数组，也是固定的，因而空间复杂度是O(1)
     */
    public static int singleNumberC(int[] A) {
        int[] digits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                digits[i] += (A[j] >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (digits[i] % 3) << i;
        }
        return res;
    }

}
