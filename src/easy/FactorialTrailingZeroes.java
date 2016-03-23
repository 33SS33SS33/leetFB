package easy;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 * Tag: Math
 */
class FactorialTrailingZeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(20));
        System.out.println(trailingZeroesB(20));
        System.out.println(trailingZeroesC(20));
        System.out.println(trailingZeroesD(20));
    }

    /**
     * O(log5-n)
     */
    public static int trailingZeroes(int n) {
        int r = 0;
        while (n > 0) {
            n /= 5;
            r += n; // add # of 5 in n
        }
        return r;
    }

    /**
     * Recursive
     */
    public static int trailingZeroesB(int n) {
        return n <= 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    public static int trailingZeroesC(int n) {
        int count = 0;
        int c2 = 0; // count of 2
        int c5 = 0; // count of 5
        for (int i = 1; i <= n; i++) {
            int m = i;
            while (m % 5 == 0) {
                m /= 5;
                c5++;
            }
            while (m % 2 == 0) {
                m /= 2;
                c2++;
            }
            int c = Math.min(c2, c5);

            count += c;
            c2 -= c;
            c5 -= c;
        }
        return count;
    }

    /**
     * creek--
     */
    public static int trailingZeroesD(int n) {
        if (n < 0)
            return -1;

        int count = 0;
        for (long i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }
}