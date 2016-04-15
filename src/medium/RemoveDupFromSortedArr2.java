package medium;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * Tags: Array, Two pointers
 */

class RemoveDupFromSortedArr2 {
    public static void main(String[] args) {
        int[] num = { 1, 1, 1, 2, 2, 3 };
        int[] num2 = { 1, 1, 1, 2, 2, 3 };
        int[] num3 = { 1, 1, 1, 2, 2, 3 };
        System.out.println(removeDuplicatesA(num));
        System.out.println(removeDuplicatesB(num2));
        System.out.println(removeDuplicatesC(num3));
    }

    /**--better--*/
    public static int removeDuplicatesC(int[] A) {
        if (A.length <= 2)
            return A.length;
        int prev = 1; // point to previous
        int curr = 2; // point to current
        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }
        return prev + 1;
    }

    /**
     * skip if length <=2
     * compare current element with second last element
     */
    public static int removeDuplicatesA(int[] A) {
        if (A == null)
            return 0;
        int n = A.length;
        if (n <= 2)
            return n; // no need to deal with n<=2 case.
        int len = 2, i = 2;
        while (i < n) {
            // compare cuurent with second last element
            if (A[i] != A[len - 2])
                A[len++] = A[i];
            i++;
        }
        return len;
    }

    public static int removeDuplicatesB(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int pre = A[0];
        boolean flag = false;
        int count = 0;
        for (int i = 1; i < A.length; i++) {
            int curr = A[i];
            if (curr == pre) {
                if (!flag) {
                    flag = true;
                    continue;
                } else {
                    count++;
                }
            } else {
                pre = curr;
                flag = false;
            }
        }
        return A.length - count;
    }
}