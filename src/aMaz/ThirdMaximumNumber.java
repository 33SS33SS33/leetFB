package aMaz;

/**
 * Created by shanshan on 17/2/17.
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not wordSearchb, return the maximum number. The time complexity must be in O(n).
 * Input: [3, 2, 1] Output: 1 Explanation: The third maximum is 1.
 * Input: [1, 2] Output: 2
 * Explanation: The third maximum does not word, so the maximum (2) is returned instead.
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        int[] m = {3, 2, 1};
        int[] n = {1, 2};
        System.out.println(thirdMaximumNumber(m));
        System.out.println(thirdMaximumNumber(n));
    }

    public static int thirdMaximumNumber(int[] nums) {
        int max, mid, small, count;
        max = mid = small = Integer.MIN_VALUE;
        count = 0;  //Count how many top elements have been found.
        for (int x : nums) {
            //Skip loop if max or mid elements are duplicate. The purpose is for avoiding right shift.
            if (x == max || x == mid) {
                continue;
            }
            if (x > max) {
                //right shift
                small = mid;
                mid = max;
                max = x;
                count++;
            } else if (x > mid) {
                //right shift
                small = mid;
                mid = x;
                count++;
            } else if (x >= small) { //if small duplicated, that's fine, there's no shift and need to increase count.
                small = x;
                count++;
            }
        }
        //"count" is used for checking whether found top 3 maximum elements.
        if (count >= 3) {
            return small;
        } else {
            return max;
        }
    }

}
