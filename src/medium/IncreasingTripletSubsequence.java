package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/3/2.
 */

/**
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Given [1, 2, 3, 4, 5],
 * return true.
 * Given [5, 4, 3, 2, 1],
 * return false.
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        IncreasingTripletSubsequence s = new IncreasingTripletSubsequence();
        int[] nums = { 1, 2, 3, 4, 5 };
        int[] nums2 = { 5, 4, 3, 2, 1 };

        System.out.println(s.increasingTriplet(nums));
        System.out.println(s.increasingTriplet(nums2));
    }

    public boolean increasingTriplet(int[] nums) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int z = nums[i];
            if (x >= z) {
                x = z;
            } else if (y >= z) {
                y = z;
            } else {
                return true;
            }
        }
        return false;
    }
}
