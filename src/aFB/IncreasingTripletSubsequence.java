package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/3/2.
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
        int[] nums = {1, 2, 3, 0, 1};
        int[] nums2 = {5, 4, 3, 2, 1};
        int[] nums3 = {-10, 1, 2, 3, -7};

        System.out.println(s.increasingTripleta(nums));
        System.out.println(s.increasingTripleta(nums2));
        System.out.println(s.increasingTripleta(nums3));
    }

    public boolean increasingTripleta(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }

}
