package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "Given an integer, write a function to determine if it is a power of three.
 * Follow up:Could you do it without using any loop / recursion?"
 * 和power of two一样 也可以直接用个3最大的次方去除一下就可以
 */
public class PowerofThree {
    public static void main(String[] args) {
        System.out.println(new PowerofThree().isPowerOfThreea(16));
        System.out.println(new PowerofThree().powerOfThree(9));
        System.out.println(new PowerofThree().powerOfThreeb(16));
    }

    /**
     * "Given an integer, write a function to determine if it is a power of three.
     * Follow up:Could you do it without using any loop / recursion?"
     * 和power of two一样 也可以直接用个3最大的次方去除一下就可以
     */
    public boolean isPowerOfThreea(int n) {
        return Integer.toString(n, 3).matches("10*");
    }

    public boolean powerOfThree(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && powerOfThree(n / 3)));
    }

    public boolean powerOfThreeb(int n) {
        if (n > 1)
            while (n % 3 == 0) n /= 3;
        return n == 1;
    }
}
