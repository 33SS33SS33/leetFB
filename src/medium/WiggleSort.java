package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/6.
 * Given a sorted array, and re-arrange it to wiggle style in one pass.
 * i.e.
 * [1] A0 >= A1 <= A2 >= A3 .... .... An.
 * [2] A0 <= A1 >= A2 <= A3 .... .... An.
 * Tags: Sort, Array
 */

//遍历一遍 当是奇数位的时候 检查一下是不是比之前的大 偶数位的时候检查是不是比之前的小
class WiggleSort {
    public static void main(String[] args) {
        int[] A = {1, 4, 5, 7, 8};
        new WiggleSort().wiggleSort(A);
        for (int i : A) {
            System.out.print(i + ",");
        }
    }

    /**
     * 最好的
     */
    public void wiggleSorta(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i])
                    swap(nums, i);
            } else if (i != 0 && nums[i - 1] < nums[i])
                swap(nums, i);
    }

    public void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;

    }

    /**
     * Swap neighbors
     * A0 >= A1 <= A2 >= A3 .... .... An.
     */
    public void wiggleSort(int[] A) {
        if (A == null || A.length == 0)
            return;
        for (int i = 0; i < A.length - 1; i += 2) {
            swap(A, i, i + 1);
        }
    }

    void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}