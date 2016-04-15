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
        System.out.println(r.reverse(a));
        System.out.println(r.reverseB(a));
        System.out.println(r.reverseC(a));
        int b = 100;
        System.out.println(r.reverse(b));
        System.out.println(r.reverseB(b));
        System.out.println(r.reverseC(b));
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

    /** 更好的
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

    /*
    * 一般来说整数的处理问题要注意的有两点，一点是符号，另一点是整数越界问题
    * 上面的代码为了后面方便处理，先将数字转为正数。注意Integer.MIN_VALUE的绝对值是比Integer.MAX_VALUE大1的，所以经常要单独处理。
    * 如果不先转为正数也可以，只是在后面要对符号进行一下判断。这种题目考察的就是数字的基本处理，面试的时候尽量不能错，
    * 而且对于corner case要尽量进行考虑，一般来说都是面试的第一道门槛*/
    public int reverseC(int x) {
        if (x == Integer.MIN_VALUE)
            return 0;
        int num = Math.abs(x);
        int res = 0;
        while (num != 0) {
            if (res > (Integer.MAX_VALUE - num % 10) / 10)
                return 0;
            res = res * 10 + num % 10;
            num /= 10;
        }
        return x > 0 ? res : -res;
    }

}
