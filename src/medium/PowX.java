package medium;

/**
 * Implement pow(x, n).
 * <p/>
 * Tags: Math, Binary Search
 */
class PowX {
    public static void main(String[] args) {
        PowX p = new PowX();
        System.out.println(p.pow(2.0, 5));
        System.out.println(p.powB(2.0, 5));
    }

    /**
     * Binary Search, divide n by 2
     * Questions:
     * 1. can x be zero?
     * 2. can n be negative?
     * <p/>
     * When n is odd, multiply result by f
     * f multiply by itself each time
     * Repeat until n == 0
     * x^10 = x^2 * x^8
     * x^9 = x * x^8
     * x^8 = x^8
     * x^7 = x * x^2 * x^4
     * x^6 = x^2 * x^4
     */
    public double pow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) { // neg case
            n = -n;
            x = 1 / x; // x can be zero?
        }
        double res = 1; // mind overflow
        for (double f = x; n > 0; n = n >> 1) { // n >>= 1, n/=2
            if (n % 2 == 1)
                res *= f;
            f = f * f; // f *= f
        }
        return res;
    }

    /**
     * creek The most understandable solution I have found so far.
     */
    public double powB(double x, int n) {
        if (n < 0) {
            return 1 / power(x, -n);
        } else {
            return power(x, n);
        }
    }

    public double power(double x, int n) {
        if (n == 0)
            return 1;
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
}
