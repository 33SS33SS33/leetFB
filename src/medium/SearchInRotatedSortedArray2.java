package medium;

/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 *
 * Write a function to determine if a given target is in the array.
 *
 * Tags: Array, Binary Search
 */
class SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        int[] A = { 4, 5, 5, 6, 7, 7, 0, 1, 2 };
        int target = 1;

        System.out.println(new SearchInRotatedSortedArray2().search(A, target));
        System.out.println(new SearchInRotatedSortedArray2().searchB(A, target));
    }

    /**
     * Clarification: non-descending order
     * Ends up same as sequential search at worst.
     */
    public boolean search(int[] A, int target) {
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

    public boolean searchB(int[] nums, int target) {
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
                left++;
            }
        }
        return false;
    }
}