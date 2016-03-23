package list;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class SearchinRotatedSortedArray {

    public static void main(String[] args) {
        SearchinRotatedSortedArray r = new SearchinRotatedSortedArray();
        int[] nums = { 4, 5, 6, 7, 1, 2, 3 };
        int k = 3;
        int t = r.search(nums, k);
        int t1 = r.searchA(nums, k);
        int t2 = r.searchB(nums, k);
        System.out.println(t);
        System.out.println(t1);
        System.out.println(t2);
    }

    /**
     * Recusive creek--
     */
    public int searchA(int[] nums, int target) {
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
            } else {
                return binarySearch(nums, mid + 1, right, target);
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                return binarySearch(nums, mid + 1, right, target);
            } else {
                return binarySearch(nums, left, mid - 1, target);
            }
        }
    }

    /**
     * Iterative  ----creek---
     */
    public int searchB(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] A, int target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int s = 0;
        int e = A.length;
        while (s < e) {
            int mid = (s + e) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (target < A[mid]) {
                // normal in first half
                if (A[s] <= A[mid] && A[s] <= target) {
                    e = mid;
                    // abnormal
                } else if (A[mid] <= A[e - 1]) {
                    e = mid;
                } else {
                    s = mid + 1;
                }
            } else {
                // normal in last half
                if (A[mid] <= A[e - 1] && target <= A[e - 1]) {
                    s = mid + 1;
                } else if (A[s] <= A[mid]) {
                    s = mid + 1;
                } else {
                    e = mid;
                }
            }
        }
        return -1;
    }
}
