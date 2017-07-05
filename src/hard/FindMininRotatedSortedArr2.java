package hard;

import java.util.Arrays;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 * Tags: Array, Binary Search
 * 和Search in Rotated Sorted Array II 基本一样 貌似可以把min的判断去掉 未实现
 * 需要仔细研究一下二分查找的start和end的设置情况 重要
 */
class FindMininRotatedSortedArr2 {
    public static void main(String[] args) {
        // int[] num = { 2, 3, 3, 4, 5, 6, 7, 0, 0, 0, 1, 1, 2, 2, 2 };
        // int[] num = { 3, 3, 1 };
        int[] num = {10, 1, 4, 10, 10, 10};
        System.out.println(new FindMininRotatedSortedArr2().findMinA(num));
        System.out.println(new FindMininRotatedSortedArr2().findMin(num));
        System.out.println(new FindMininRotatedSortedArr2().findMinB(num));
    }

    /**
     * 最好的
     */
    public int findMinA(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
            }
        }
        return nums[l];
    }

    /**
     * Skip all the indentical elements on the left in each search
     */
    public int findMin(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int l = 0;
        int r = num.length - 1;
        while (l <= r) {
            int k = l;
            while (k <= r && num[k] == num[r])
                k++;
            if (k > r)
                return num[l];
            l = k;
            if (num[l] < num[r])
                return num[l];
            int mid = l + (r - l) / 2;
            if (num[mid] >= num[l])
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    /**
     * creek
     */
    public int findMinB(int[] num) {
        return findMin(num, 0, num.length - 1);
    }

    public int findMin(int[] num, int left, int right) {
        if (right == left) {
            return num[left];
        }
        if (right == left + 1) {
            return Math.min(num[left], num[right]);
        }
        // 3 3 1 3 3 3
        int middle = (right - left) / 2 + left;
        // already sorted
        if (num[right] > num[left]) {
            return num[left];
            //right shift one
        } else if (num[right] == num[left]) {
            return findMin(num, left + 1, right);
            //go right
        } else if (num[middle] >= num[left]) {
            return findMin(num, middle, right);
            //go left
        } else {
            return findMin(num, left, middle);
        }
    }

}
