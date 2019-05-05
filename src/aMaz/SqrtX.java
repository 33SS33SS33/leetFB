package aMaz;

class SqrtX {
    public static void main(String[] args) {
        int[] nums = {-1, 1, 2, 4, 9, 16, 25};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(sqrtX(nums[i]));
        }
    }

    /**
     * Implement int sqrtX(int x).
     * Compute and return the square root of x.
     * Tags: Math, Binary Search
     * <p>
     * 1. Validate input first
     * 2.Binary Search from 1 ~ x
     * 3.Negative?
     * 4.Perfect square?
     * Note possible overflows when mid * mid or (left + right) / 2.
     */
    public static int sqrtX(int x) {
        if (x < 0)
            return -1; // if (x <= 0) return x;
        if (x == 0)
            return 0;
        int left = 1; // search range
        int right = x;
        int mid;
        while (left <= right) { // can equal
            mid = left + (right - left) / 2; // left + right can overflow
            if (mid == x / mid)
                return mid; // mid * mid can overflow
            else if (mid > x / mid)
                right = mid - 1; // not right = mid
            else
                left = mid + 1; // break equal
        }
        return right;
    }

}