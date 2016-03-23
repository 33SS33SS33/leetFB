package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        int a = 43261596;
        System.out.println(r.reverseB(a));

        int b = 1;
        System.out.println(r.reverse(b));
    }

    public int reverse(int x) {
        if (x == Integer.MIN_VALUE)
            return 0;
        if (x < 0)
            return -reverse(-x);
        int y = 0;
        do {
            // y * 10 + x % 10 > Integer.MAX_VALUE
            if (y > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            y = y * 10 + x % 10;
            x /= 10;
        } while (x > 0);
        return y;
    }

    /**
     * creek This solution is from Sherry, it is succinct and it is pretty.
     */
    public int reverseB(int x) {
        int rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }

        return rev;
    }
}
