package aMaz;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
class MergeSortedArray {
    public static void main(String[] args) {
        int A[] = new int[7];
        int B[] = {1, 3, 9};
        mergeSortedArray(A, 4, B, 3);

        for (int i : A) {
            System.out.print(i);
        }
    }

    //最好的 算法的时间复杂度是O(m+n),m和n分别是两个数组的长度，空间复杂度是O(1)
    public static int[] mergeSortedArray(int[] A, int m, int[] B, int n) {
        int[] res = new int[m + n];
        if (A == null || B == null)
            return res;
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i > -1 && j > -1) {
            A[k--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while (j > -1) {
            A[k--] = B[j--];
        }
        return A;
    }
/*
    public void static main(String[] args){
        int[] A={1,3,7};
        int[] B={2,4,9,11};
        int m=3, n=4;
        int[] res=merge(A,m,B,n);

    }*/
}
