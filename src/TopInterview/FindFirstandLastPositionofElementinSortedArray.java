package TopInterview;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 * Tags: Array, Binary Search
 */
class FindFirstandLastPositionofElementinSortedArray {
    public static void main(String[] args) {
        FindFirstandLastPositionofElementinSortedArray s = new FindFirstandLastPositionofElementinSortedArray();
        int[] A = {5, 7, 7, 8, 8, 10};
        int[] range1 = s.searchForARangeb(A, 8);
        System.out.println(range1[0] + " ~ " + range1[1]);
    }

    //http://blog.csdn.net/linhuanmars/article/details/20593391
    public int[] searchForARangeb(int[] A, int target) {
        int start = firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            //low <= mid < high
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }
}
