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
        int[] num = {1, 1, 1, 2, 2, 3};
        int[] num1 = {1, 1, 1, 2, 2, 3};
        int[] num2 = {1, 1, 1, 2, 2, 3};
        int[] num3 = {1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(num));
        System.out.println(RemoveDupFromSortedArr2a(num1));
    }

    /**
     * 最好的
     * skip if length <=2
     * compare current element with second last element
     */
    public static int RemoveDupFromSortedArr2a(int[] A) {
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

    //最简单的???
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }

}