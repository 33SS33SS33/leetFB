package other;

import java.util.*;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 * Tags: Divide and Conquer, Heap
 * Similar Problems: (M) Wiggle Sort II
 */
class KthLargest {
    public static void main(String[] args) {
        KthLargest K = new KthLargest();
        int[] A = { 1, 23, 12, 9, 30, 2, 50 };
        System.out.println(K.findKthLargest(A, 3));
        System.out.println(K.findKthLargestB(A, 3));
        System.out.println(K.findKthLargestC(A, 3));
    }

    /** 最好的
     * Priority Queue
     * O(n) + k * O(logn)
     */
    public int findKthLargest(int[] A, int k) {
        if (k <= 0 || k > A.length)
            return -1;
        Queue<Integer> q = new PriorityQueue<Integer>(A.length, Collections.reverseOrder());
        for (int n : A)
            q.add(n);
        int res = 0;
        for (int i = 0; i < k; i++)
            res = q.poll();
        return res;
    }

    /**
     * QuickSelect
     * Use partition algorithm in Quick Sort
     * Compare partition index with k - 1
     * If index > k - 1, means upper bound can be index - 1
     * If index < k - 1, means lower bound can be index + 1
     * If index == k - 1, return that number
     */
    public int findKthLargestB(int[] A, int k) {
        if (k <= 0 || k > A.length)
            return -1;
        int l = 0; // initialize
        int r = A.length - 1;
        int index;
        while (l < r) {
            index = partition(A, l, r);
            if (index > k ) {
                r = index - 1;
            } else if (index < k ) {
                l = index + 1;
            } else {
                return A[index];
            }
        }
        return A[l];
    }
    /**
     * Choose mid value as pivot
     * Move two pointers
     * Swap and move on
     * Return left pointer
     */
    private int partition(int[] a, int left, int right) {
        int pivot = a[left + (right - left) / 2];
        while (left <= right) {
            while (a[left] > pivot)
                left++;
            while (a[right] < pivot)
                right--;
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    public int findKthLargestC(int[] nums, int k) {
        if (k < 1 || nums == null) {
            return 0;
        }
        return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
    }
    public int getKth(int k, int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start;
        int right = end;
        while (true) {
            while (nums[left] < pivot && left < right) {
                left++;
            }
            while (nums[right] >= pivot && right > left) {
                right--;
            }
            if (left == right) {
                break;
            }
            swap(nums, left, right);
        }
        swap(nums, left, end);
        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    public void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}
