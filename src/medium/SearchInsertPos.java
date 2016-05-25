package medium;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * Tags: Array, Binary Search
 */
class SearchInsertPos {
    public static void main(String[] args) {
        int[] A = { 1, 3, 5, 6 };
        int target = 5;
        SearchInsertPos a = new SearchInsertPos();
        System.out.println(a.searchInsert(A, target));
        System.out.println(a.searchInsertB(A, target));
        System.out.println(a.searchInsertC(A, target));
    }

    /**
     * 二分法迭代
     * Binary search
     * r = m - 1, l = m + 1
     * 复杂度是O(logn)，空间复杂度O(1)
     */
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target)
                return m;
            else if (A[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }

    /**
     * 递归 二分法
     * This also looks like a binary search problem. We should try to make the complexity to be O(log(n)).
     */
    public int searchInsertC(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        return searchInsert(A, target, 0, A.length - 1);
    }

    public int searchInsert(int[] A, int target, int start, int end) {
        int mid = (start + end) / 2;
        if (target == A[mid])
            return mid;
        else if (target < A[mid])
            return start < mid ? searchInsert(A, target, start, mid - 1) : start;
        else
            return end > mid ? searchInsert(A, target, mid + 1, end) : (end + 1);
    }

    /**
     * 不好的
     * Naively, we can just iterate the array and compare target with ith and (i+1)th element. Time complexity is O(n)
     */
    public int searchInsertB(int[] A, int target) {
        if (A == null)
            return 0;
        if (target <= A[0])
            return 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (target > A[i] && target <= A[i + 1]) {
                return i + 1;
            }
        }
        return A.length;
    }

}