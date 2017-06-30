package hard;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Tags: Divide and Conquer, Array, Binary Search
 */
class MedianOfTwoSortedArrs {
    public static void main(String[] args) {
        MedianOfTwoSortedArrs m = new MedianOfTwoSortedArrs();
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 4, 5, 6, 7};
        System.out.println(m.findMedianSortedArrays(A, B));
        System.out.println(m.findMedianSortedArraysB(A, B));
    }
    /**-----------------------------------------------------------------------------------
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
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m)
            return findMedianSortedArrays(B, A); // shorter array first
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
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE,
                k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if ((n + m) % 2 == 1)
            return (double) a; // odd
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE,
                k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // even
    }

    /**
     * -----creek-----
     */
    public static double findMedianSortedArraysB(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        if ((m + n) % 2 != 0) // odd
            return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
        else { // even
            return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) + findKth(A, B, (m + n) / 2 - 1,
                    0, m - 1, 0, n - 1)) * 0.5;
        }
    }

    public static int findKth(int A[], int B[], int k, int aStart, int aEnd, int bStart, int bEnd) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;
        // Handle special cases
        if (aLen == 0)
            return B[bStart + k];
        if (bLen == 0)
            return A[aStart + k];
        if (k == 0)
            return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
        int aMid = aLen * k / (aLen + bLen); // a's middle count
        int bMid = k - aMid - 1; // b's middle count
        // make aMid and bMid to be array index
        aMid = aMid + aStart;
        bMid = bMid + bStart;
        if (A[aMid] > B[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }
        return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
    }

}
