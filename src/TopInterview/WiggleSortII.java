package TopInterview;

/**
 * "Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?"
 * "大体思路是这样 首先要找出来median 然后用median做一个3way palindromePartition 这样的话
 * 数组就被分成了两半 前一部分是比median大的 后面哪部分是比median小的  然后就把后一部分和前一部分穿插起来就行了
 * 但是代码跑的空间不够了   应该是快速选择的时候有问题  未实现"
 * Given a random array,re-arrange it to wiggle style.
 * i.e.
 * [1]A0>=A1<=A2>=A3.... ....An.
 * [2]A0<=A1>=A2<=A3.... ....An.
 * Tags:Sort,Array
 */

class WiggleSortII {
    public static void main(String[] args) {
//        int[] A = {1, 2, 8, 9, 3, 5};
        int[] A = {1, 3, 2, 2, 3, 1};
        new WiggleSortII().wiggleSortUnsorted(A);
        for (int i = 0; i < A.length; i++) {
            System.out.print(i == A.length - 1 ? A[i] : A[i] + ", ");
        }
        System.out.println();
    }

    /**
     * One-pass Solution.
     * First 2 elements already sorted, start from second element
     * For A[n-1], A[n], A[n+1], n >= 1, n < length - 1
     * Suppose sorted sequence are m1, m2, m3
     * If A[n-1] > A[n], thus A[n-2] < A[n-1], A[n-1]=m3, A[n]=m1, A[n+1]=m2
     * So A[n-2] < A[n-1] > A[n] < A[n+1]
     * If A[n-1] < A[n], thus A[n-2] > A[n-1], A[n-1]=m1, A[n]=m3, A[n+1]=m2
     * So A[n-2] > A[n-1] < A[n] > A[n+1]
     */
    public void wiggleSortUnsorted(int[] A) {
        if (A == null || A.length == 0)
            return;
        for (int i = 1; i < A.length - 1; i++) {
            int m1 = Math.min(A[i - 1], Math.min(A[i], A[i + 1])); // min
            int m3 = Math.max(A[i - 1], Math.max(A[i], A[i + 1])); // max
            int m2 = A[i - 1] + A[i] + A[i + 1] - m1 - m3; // middle
            if (A[i - 1] > A[i]) {
                A[i - 1] = m3;
                A[i] = m1;
                A[i + 1] = m2;
            } else {
                A[i - 1] = m1;
                A[i] = m3;
                A[i + 1] = m2;
            }
        }
    }
}

