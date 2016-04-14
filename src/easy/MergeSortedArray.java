package easy;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m +
 * n) to hold additional elements from B. The number of elements initialized in
 * A and B are m and n respectively.
 * Tags: Array
 */
class MergeSortedArray {
    public static void main(String[] args) {
        int A[] = new int[7];
        int B[] = { 1, 3, 9 };
        new MergeSortedArray().merge(A, 4, B, 3);

        for (int i : A) {
            System.out.print(i);
        }
    }

    /**
     * Merge from behind
     */
    private void merge(int A[], int m, int B[], int n) {
        if (n == 0)
            return;
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }
        while (n > 0)/* still elements in B  m==0*/
            A[n - 1] = B[n-- - 1];
    }

    /**creek 2*/
    public static void mergeB(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (k >= 0) {
            if (j < 0 || (i >= 0 && A[i] > B[j]))
                A[k--] = A[i--];
            else
                A[k--] = B[j--];
        }
    }

    /*算法的时间复杂度是O(m+n),m和n分别是两个数组的长度，空间复杂度是O(1)。*/
    public void mergeB2(int A[], int m, int B[], int n) {
        if (A == null || B == null)
            return;
        int idx1 = m - 1;
        int idx2 = n - 1;
        int len = m + n - 1;
        while (idx1 >= 0 && idx2 >= 0) {
            if (A[idx1] > B[idx2]) {
                A[len--] = A[idx1--];
            } else {
                A[len--] = B[idx2--];
            }
        }
        while (idx2 >= 0) {
            A[len--] = B[idx2--];
        }
    }

}
