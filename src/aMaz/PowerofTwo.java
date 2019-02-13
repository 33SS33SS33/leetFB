package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an integer, write a function to determine if it is a power of two.
 * Input: 1 Output: true
 * Explanation: 20 = 1
 * Input: 16 Output: true
 * Explanation: 24 = 16
 * Input: 218 Output: false
 * 要么就是用1右移32位 看有没有
 * 更好的办法是 n&(n-1) == 0 因为如果是2的n次方 那么这个数一定是1开头 后面全是0
 */
public class PowerofTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo1(5));
        System.out.println(isPowerOfTwoa(5));
        System.out.println(isPowerOfTwo(5));
    }

    //??
    public static boolean isPowerOfTwo1(int n) {
        return n > 0 && (n ^ (n & -n)) == 0;
    }

    //recursion
    public static boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;
        if (n % 2 == 1)
            return false;
        return isPowerOfTwo(n / 2);
    }

    //TODO
    public static boolean isPowerOfTwoa(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }


}
