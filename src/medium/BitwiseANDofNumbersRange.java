package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive
 */
public class BitwiseANDofNumbersRange {
    public static void main(String[] args) {

        System.out.println(rangeBitwiseAnd(5, 7));
        System.out.println(rangeBitwiseAndB(5, 7));
        System.out.println(rangeBitwiseAnd(2, 3));
        System.out.print(rangeBitwiseAndB(2, 3));
    }

    static final int    SIZE = Integer.SIZE;
    static final long[] POW  = new long[SIZE + 1];

    static {
        for (int i = 0; i < SIZE; i++) {
            POW[i] = (long) Math.pow(2, i);
        }
    }

    public static int rangeBitwiseAnd(int m, int n) {
        for (int i = SIZE; i > 0; i--) {
            if (POW[i - 1] <= m && m < POW[i]) {
                if (POW[i - 1] <= n && n < POW[i]) {
                    long p = POW[i - 1];
                    return (int) p | rangeBitwiseAnd((int) (m & (p - 1)), (int) (n & (p - 1)));
                }
            }
        }
        return 0;
    }

    /**
     * creek--
     */
    public static int rangeBitwiseAndB(int m, int n) {
        while (n > m) {
            n = n & n - 1;
        }
        return m & n;
    }
}
