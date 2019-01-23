package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to searchinRotatedSortedArrayb. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class SearchinRotatedSortedArray {
    public static void main(String[] args) {
        SearchinRotatedSortedArray r = new SearchinRotatedSortedArray();
        int[] nums = { 4, 5, 6, 7, 1, 2, 3 };
        int k = 3;
        int t1 = r.searchinRotatedSortedArray(nums, k);
        int t2 = r.searchinRotatedSortedArrayb(nums, k);
        System.out.println(t1);
        System.out.println(t2);
    }

    /**
     * Iterative creek 迭代
     * 在每次迭代中，分三种情况：
     * （1）如果target==A[m]，那么m就是我们要的结果，直接返回；
     * （2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），
     * 那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
     * （3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
     */
    public static int searchinRotatedSortedArrayb(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target)
                return m;
            if (A[m] >= A[l]) { // left half sorted
                if (target >= A[l] && target < A[m]) {
                    r = m - 1;
                }
                else
                    l = m + 1;
            }
            else { // right half sorted
                if (target > A[m] && target <= A[r]) {
                    l = m + 1;
                }
                else
                    r = m - 1;
            }
        }
        return -1;
    }

    /**
     * Recusive creek 递归
     */
    public int searchinRotatedSortedArray(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (target == nums[mid])
            return mid;
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                return binarySearch(nums, left, mid - 1, target);
            }
            else {
                return binarySearch(nums, mid + 1, right, target);
            }
        }
        else {
            if (nums[mid] < target && target <= nums[right]) {
                return binarySearch(nums, mid + 1, right, target);
            }
            else {
                return binarySearch(nums, left, mid - 1, target);
            }
        }
    }

}
