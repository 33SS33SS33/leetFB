package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/12.
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

//需要一个zero来记录当前最左边的0的位置 如果碰到了不是0的 就置换 然后zero往后移动一位
public class MoveZeroes {
    public static void main(String[] args) {
        int[] num = {1, 4, 3, 0, 8, 1, 0, 0};
        new MoveZeroes().moveZeroes1(num);
        for (int i : num) {
            System.out.print(i + ",");
        }
    }

    /**
     * best 最好的
     */
    //https://discuss.leetcode.com/topic/24716/simple-o-n-java-solution-using-insert-index/2
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0)
                nums[insertPos++] = num;
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

}
