package medium;

/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 * Tags: Array, Binary Search
 * 假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，这样的我们判断左边缘和中心的时候都是3，
 * 如果我们要寻找1或者2，我们并不知道应该跳向哪一半。解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。
 * 所以最坏情况（比如全部都是一个元素，或者只有一个元素不同于其他元素，而他就在最后一个）就会出现每次移动一步，总共是n步，算法的时间复杂度变成O(n)
 */
//这道题目中元素会有重复的情况出现
class SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        int[] A = {4, 5, 5, 6, 7, 7, 0, 1, 2};
        int target = 3;
        System.out.println(new SearchInRotatedSortedArray2().searchA(A, target));
        System.out.println(new SearchInRotatedSortedArray2().searchB(A, target));
    }

    /**
     * better
     */
    public boolean searchA(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;/**重要步骤 平移*/
            }
        }
        return false;
    }

    /**
     * Clarification: non-descending order
     * Ends up same as sequential search at worst.
     */
    public boolean searchB(int[] A, int target) {
        if (A == null || A.length == 0)
            return false;
        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (A[m] == target)
                return true;
            /*skip*/
            if (A[l] == A[m] && A[m] == A[r]) {
                l++;
                r--;
            } else if (A[l] == A[m])
                l = m + 1;
            else if (A[m] == A[r])
                r = m;
            else if (A[l] < A[m]) { // left half sorted
                if (A[l] <= target && target < A[m])
                    r = m - 1;
                else
                    l = m + 1;
            } else if (A[l] > A[m]) { // right half sorted
                if (A[m] < target && target <= A[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
        }
        return false;
    }

}