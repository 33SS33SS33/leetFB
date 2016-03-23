package easy;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * <p/>
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m +
 * n) to hold additional elements from B. The number of elements initialized in
 * A and B are m and n respectively.
 * <p/>
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
        /** still elements in B  m==0*/
        while (n > 0)
            A[n - 1] = B[n-- - 1];
    }

    /**
     * creek 1
     */
    public static void mergeA(int A[], int m, int B[], int n) {
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }

        while (n > 0) {
            A[m + n - 1] = B[n - 1];
            n--;
        }
    }

    /**
     * creek 2
     */
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
}
