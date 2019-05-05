package aMaz;


class MedianofTwoSortedArrays {
    public static void main(String[] args) {
        MedianofTwoSortedArrays m = new MedianofTwoSortedArrays();
        int[] A1 = {1, 2};
        int[] A = {1, 2, 3, 4, 5};
        int[] B1 = {3, 4};
        int[] B = {2, 4, 5, 6, 7};
        System.out.println(m.medianofTwoSortedArrays(A, B));
        System.out.println(m.medianofTwoSortedArrays(A1, B1));
    }

    /**
     * 1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
     2) If m1 and m2 both are equal then we are done, and return m1 (or m2)
     3) If m1 is greater than m2, then median is present in one of the below two subarrays.
     a) From first element of ar1 to m1 (ar1[0...|_n/2_|])
     b) From m2 to last element of ar2 (ar2[|_n/2_|...n-1])
     4) If m2 is greater than m1, then median is present in one of the below two subarrays.
     a) From m1 to last element of ar1 (ar1[|_n/2_|...n-1])
     b) From first element of ar2 to m2 (ar2[0...|_n/2_|])
     5) Repeat the above process until size of both the subarrays becomes 2.
     6) If size of the two arrays is 2 then use below formula to get the median.
     Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
     --------------------------------------------------------------------------------------*/

    /**
     * Search in shorter array
     * Find 4 possible candidates A[l-1], A[l], B[k-1], B[k-l+1]
     * If total # of items is odd, return the max of A[l-1] and B[k-1], a
     * If total # of items is even, get the min of A[l] and B[k-l+1], b
     * Return the average of a and b
     * <p>
     * HARD, There are two sorted arrays A and B of size m and n respectively. Find the
     * median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * You may assume nums1 and nums2 cannot be both empty.
     * nums1 = [1, 3]  nums2 = [2]  The median is 2.0
     * nums1 = [1, 2]  nums2 = [3, 4] The median is (2 + 3)/2 = 2.5
     * Tags: Divide and Conquer, Array, Binary Search
     */
    public double medianofTwoSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m)
            return medianofTwoSortedArrays(B, A); // shorter array first
        int k = (n + m - 1) / 2; // mid position
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        // find A[l] > B[k - l]
        while (l < r) {
            int midA = l + (r - l) / 2; // A[i], avoid overflow
            int midB = k - midA; // B[j - 1]
            if (A[midA] < B[midB])
                l = midA + 1; // i + 1, r
            else
                r = midA; // l, i
        }
        // A[l-1], A[l], B[k-l], and B[k-l+1] 
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if ((n + m) % 2 == 1)
            return (double) a; // odd
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // even
    }

}
