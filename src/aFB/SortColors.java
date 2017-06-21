package aFB;

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * Tags: Array, Two Pointers, Sort
 */

class SortColors {
    public static void main(String[] args) {
        SortColors s = new SortColors();
        // normal case
        int[] A = {0, 1, 0, 1, 2, 1, 0};
        int[] A2 = {0, 1, 0, 1, 2, 1, 0};
        int[] A3 = {0, 1, 0, 1, 2, 1, 0};
        int[] A4 = {0, 1, 0, 1, 2, 1, 0};
        // other test cases
        // int[] A = {1, 2, 0};
        // int[] A = {2};
        // int[] A = {2, 2};
        s.onePassSortColors(A);
        for (int i : A) {
            System.out.print(i);
        }
        System.out.println();
        s.sortColorsA(A2);
        for (int i : A2) {
            System.out.print(i);
        }
        System.out.println();
        s.sortColorsB(A3);
        for (int i : A3) {
            System.out.print(i);
        }
        System.out.println();
        s.sortColors(A4);
        for (int i : A4) {
            System.out.print(i);
        }
    }

    /**
     * 最好的 理解了 哈哈哈~~~
     * One-pass count sorting
     * Remember the count of red, and count of red + white
     */
    public void onePassSortColors(int[] A) {
        if (A == null || A.length == 0)
            return;
        int i = -1; // red count, start of white
        int j = -1; // red + white count, start of blue
        for (int k = 0; k < A.length; k++) {
            int v = A[k];
            A[k] = 2; // overwrite as blue
            if (v == 0) {
                A[++j] = 1; // write white first, then red
                A[++i] = 0; // overwrite 1 when there is no white yet
            } else if (v == 1)
                A[++j] = 1;
        }
    }

    /**
     * 简单的
     * two-pass count sorting
     */
    public void sortColors(int[] A) {
        int red = 0;
        int white = 0;
        int blue = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                red++;
            } else if (A[i] == 1) {
                white++;
            } else {
                blue++;
            }
        }
        int i = 0;
        while (i < red) {
            A[i++] = 0;
        }
        while (i < white + red) {
            A[i++] = 1;
        }
        while (i < white + red + blue) {
            A[i++] = 2;
        }
    }

    /**
     * creek  improved 计数排序
     */
    public void sortColorsA(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] countArray = new int[3];
        for (int i = 0; i < nums.length; i++) {
            countArray[nums[i]]++;
        }
        int j = 0;
        int k = 0;
        while (j <= 2) {
            if (countArray[j] != 0) {
                nums[k++] = j;
                countArray[j] = countArray[j] - 1;
            } else {
                j++;
            }
        }
    }

    /**
     * creek 计数排序----
     */
    public void sortColorsB(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] countArray = new int[3];
        for (int i = 0; i < nums.length; i++) {
            countArray[nums[i]]++;
        }
        for (int i = 1; i <= 2; i++) {
            countArray[i] = countArray[i - 1] + countArray[i];
        }
        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = countArray[nums[i]] - 1;
            countArray[nums[i]] = countArray[nums[i]] - 1;
            sorted[index] = nums[i];
        }
        System.arraycopy(sorted, 0, nums, 0, nums.length);
    }

}
