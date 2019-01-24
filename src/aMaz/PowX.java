package aMaz;

/**
 * Implement pow(x, n).
 * Tags: Math, Binary Search
 * 使用二分法处理, 注意n<0的情况
 * n每次都除以2 直到等于0 然后返回1
 * 然后依次返回乘积 每个状态下都注意n的奇偶问题即可
 */
class PowX {
    public static void main(String[] args) {
        PowX p = new PowX();
        System.out.println(p.powa(2.0, 10));
        System.out.println(p.power(2.0, 5));
    }

    public double power(double x, int n) {
        if (n == 0)
            return 1;
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        }
        else {
            return v * v * x;
        }
    }

    /**
     * 递归
     */
    public double powa(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
  /*          if (-n == Integer.MIN_VALUE) {
                return x * powa(x, (n - 1));
            }*/
        }
        return (n % 2 == 0) ? powa(x * x, n / 2) : x * powa(x * x, n / 2);
    }

}
