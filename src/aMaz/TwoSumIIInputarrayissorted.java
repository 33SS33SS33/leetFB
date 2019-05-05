package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class TwoSumIIInputarrayissorted {
    public static void main(String[] args) {
        TwoSumIIInputarrayissorted t = new TwoSumIIInputarrayissorted();
        int[] numbers = {2, 4, 7}; // 6 = 2+4
        int target = 6;
        int[] res1 = t.twoSumInputArrayIsSorted(numbers, target);
        for (int i = 0; i < res1.length; i++) {
            System.out.print(i == res1.length - 1 ? res1[i] : res1[i] + ", ");
        }
    }

    /**
     * 还是two sum的题 直接用了哈希表来解 也可以用two pointer 还有binary search
     * input array is sorted creek!
     */
    public int[] twoSumInputArrayIsSorted(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0)
            return null;
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int x = numbers[i] + numbers[j];
            if (x < target) {
                ++i;
            } else if (x > target) {
                j--;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }

}
