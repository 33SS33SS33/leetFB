package TopInterview;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Reverse digits of an integer.
 * Example1: x = 123, return 321  x = -123, return -321
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        int a = 43261596;
        System.out.println(r.reverseInteger(a));
        int b = -190;
        System.out.println(r.reverseInteger(b));
    }

    public int reverseInteger(int x) {
        int rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return rev;
    }

}
