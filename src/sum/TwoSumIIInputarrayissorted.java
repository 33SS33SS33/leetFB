package sum;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class TwoSumIIInputarrayissorted {

    public static void main(String[] args) {
        TwoSumIIInputarrayissorted t = new TwoSumIIInputarrayissorted();

        int[] numbers = { 2, 4, 7 }; // 6 = 2+4
        int target = 6;

        int[] res = t.twoSum(numbers, target);
        int[] res1 = t.twoSumB(numbers, target);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }
        System.out.println();
        for (int i = 0; i < res1.length; i++) {
            System.out.print(i == res1.length - 1 ? res1[i] : res1[i] + ", ");
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else { // ==
                return new int[] { i + 1, j + 1 };
            }
        }
        throw new RuntimeException();
    }

    /**
     * input array is sorted creek!
     */
    public int[] twoSumB(int[] numbers, int target) {
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
                return new int[] { i + 1, j + 1 };
            }
        }
        return null;
    }
}
