package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * 要么就是用1右移32位 看有没有
 * 更好的办法是 n&(n-1) == 0 因为如果是2的n次方 那么这个数一定是1开头 后面全是0
 */
public class PowerofTwo {
    public static void main(String[] args) {
        boolean result = isPowerOfTwo(5);
        System.out.println(result);
        boolean result2 = isPowerOfTwoB(5);
        System.out.print(result2);
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;
        if (n % 2 == 1)
            return false;
        return isPowerOfTwo(n / 2);
    }

    /**
     * creek---
     */
    public static boolean isPowerOfTwoB(int n) {
        if (n <= 0)
            return false;
        while (n > 2) {
            int t = n >> 1;
            int c = t << 1;
            if (n - c != 0)
                return false;
            n = n >> 1;
        }
        return true;
    }

}
