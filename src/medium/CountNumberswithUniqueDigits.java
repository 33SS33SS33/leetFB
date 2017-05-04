package medium;

/**
 * Created by shanshan on 16/6/18.
 * <p>
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers
 * in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 */

public class CountNumberswithUniqueDigits {
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigitsa(2));
        System.out.println(countNumbersWithUniqueDigits(2));
    }

    //Backtracking
    public static int countNumbersWithUniqueDigitsa(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigitsa(10);
        }
        int count = 1; // x == 0
        long max = (long) Math.pow(10, n);
        boolean[] used = new boolean[10];
        for (int i = 1; i < 10; i++) {
            used[i] = true;
            count += search(i, max, used);
            used[i] = false;
        }
        return count;
    }

    private static int search(long prev, long max, boolean[] used) {
        int count = 0;
        if (prev < max) {
            count += 1;
        } else {
            return count;
        }
        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                used[i] = true;
                long cur = 10 * prev + i;
                count += search(cur, max, used);
                used[i] = false;
            }
        }
        return count;
    }

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;

        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
}
