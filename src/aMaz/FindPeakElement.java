package aMaz;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Input: nums = [1,2,1,3,5,6,4] Output: 1 or 5
 * Note:
 * Your solution should be in logarithmic complexity.
 * Tags: Array, Binary Search
 * 题目要求lgn的解法 所以就用二分查找
 * 注意越界的问题 所以设置了先检查首尾元素是否符合条件 符合就返回
 * 如果不符合就二分查找 二分查找的界限是从1开始到倒数第二个元素结束
 * 这道题第一次写的解法是错的  这道题如果mid不是peak的话 只需要看右边的那个元素是不是大于mid
 * 如果大于mid 那么右边一定会有一个peak
 * 因为如果右边是个递增到结束的数列 那么结尾的那个就是peak 否则就往前找
 */
class FindPeakElement {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 1, 4};
        System.out.println(new FindPeakElement().findPeakElementa(num));
        System.out.println(new FindPeakElement().findPeakElement(num));
        System.out.println(new FindPeakElement().findPeakElementC(num));
    }

    public int findPeakElementa(int[] num) {
        return helper(num, 0, num.length - 1);
    }

    public int helper(int[] num, int start, int end) {
        if (start == end) {
            return start;
        } else if (start + 1 == end) {
            if (num[start] > num[end])
                return start;
            return end;
        } else {
            int m = (start + end) / 2;
            if (num[m] > num[m - 1] && num[m] > num[m + 1]) {
                return m;
            } else if (num[m - 1] > num[m] && num[m] > num[m + 1]) {
                return helper(num, start, m - 1);
            } else {
                return helper(num, m + 1, end);
            }
        }
    }

    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int n = num.length;
        if (n <= 1)
            return 0;
        // handle the first and last element in num[]
        if (num[0] > num[1])
            return 0;
        if (num[n - 1] > num[n - 2])
            return n - 1;
        int left = 1, right = n - 2;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1])
                return mid;
            else if (num[mid] > num[mid + 1])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    public int findPeakElementC(int[] num) {
        return findPeakElement(num, 0, num.length);
    }

    int findPeakElement(int[] num, int from, int to) {
        if (to - from == 1)
            return from;
        int m = (to + from) / 2;
        int l = findPeakElement(num, from, m);
        int r = findPeakElement(num, m, to);
        if (num[l] > num[r])
            return l;
        else
            return r;
    }

}