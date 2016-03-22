package medium;

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * <p/>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p/>
 * If the target is not found in the array, return [-1, -1].
 * <p/>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * <p/>
 * Tags: Array, Binary Search
 */
class SearchForARange {
    public static void main(String[] args) {
        SearchForARange s = new SearchForARange();
        int[] A = { 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        int[] range = s.searchRange(A, 3);
        int[] range2 = s.searchRangeB(A, 3);
        System.out.println(range[0] + " ~ " + range[1]);
        System.out.println(range2[0] + " ~ " + range[1]);
    }

    /**
     * Suppose we have a binary search helper method
     * With array, start index, end index, and target as arguments
     * We can first search for the target in the whole array
     * If found, then search for its starting position
     * Then search for its ending position
     * Update range with search result and return
     */
    public int[] searchRange(int[] A, int target) {
        int[] range = { -1, -1 };
        if (A == null || A.length == 0)
            return range;
        int index = binarySearch(A, 0, A.length - 1, target);
        if (index != -1) {
            int left = index;
            int right = index;
            range[0] = left; // if no more occurrence, set left and right first
            range[1] = right;
            while ((left = binarySearch(A, 0, left - 1, target)) != -1)
                range[0] = left;
            while ((right = binarySearch(A, right + 1, A.length - 1, target)) != -1)
                range[1] = right;
        }
        return range;
    }

    private int binarySearch(int[] A, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    public int[] searchRangeB(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;
        binarySearch(nums, 0, nums.length - 1, target, arr);
        return arr;
    }

    public void binarySearch(int[] nums, int left, int right, int target, int[] arr) {
        if (right < left)
            return;
        if (nums[left] == nums[right] && nums[left] == target) {
            arr[0] = left;
            arr[1] = right;
            return;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            binarySearch(nums, mid + 1, right, target, arr);
        } else if (nums[mid] > target) {
            binarySearch(nums, left, mid - 1, target, arr);
        } else {
            arr[0] = mid;
            arr[1] = mid;
            //handle duplicates - left
            int t1 = mid;
            while (t1 > left && nums[t1] == nums[t1 - 1]) {
                t1--;
                arr[0] = t1;
            }
            //handle duplicates - right
            int t2 = mid;
            while (t2 < right && nums[t2] == nums[t2 + 1]) {
                t2++;
                arr[1] = t2;
            }
            return;
        }
    }
}
