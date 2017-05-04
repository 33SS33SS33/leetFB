package aFB;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * Tags: Array, Two pointers
 * 设置一个变量 是记录新的数组的末端的指针
 * 注意counter-2的运用 这样就可以确认当前的nums[counter]有没有超过两个
 */

class RemoveDupFromSortedArr2 {
    public static void main(String[] args) {
        int[] num = { 1, 1, 1, 2, 2, 3 };
        int[] num1 = { 1, 1, 1, 2, 2, 3 };
        int[] num2 = { 1, 1, 1, 2, 2, 3 };
        int[] num3 = { 1, 1, 1, 2, 2, 3 };
        System.out.println(removeDuplicates(num));
        System.out.println(removeDuplicatesA(num1));
        System.out.println(removeDuplicatesB(num2));
        System.out.println(removeDuplicatesC(num3));
    }

    //最简单的???
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }

    /**
     * 最好的
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
            // compare current with second last element
            if (A[i] != A[len - 2])
                A[len++] = A[i];
            i++;
        }
        return len;
    }

    /**
     * --better--
     */
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