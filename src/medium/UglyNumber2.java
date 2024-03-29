package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * "Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number."
 * "DP 动态规划
 * Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 * L1 L2 L3就是当前数组里分别指向 2 3 5的系数的指针 所有后面的数字都是前面的数字乘以2 3 5的出来的 所以要指向数组里的数字
 * 不能用个自然数当系数 然后自增
 * if判断很关键 不能用elif 否则会出现两个6  用if就是为了跳过重复"
 */
public class UglyNumber2 {
    public static void main(String[] args) {
        System.out.print(nthUglyNumber(3));
    }

    public static int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if (factor2 == min)
                factor2 = 2 * ugly[++index2];
            if (factor3 == min)
                factor3 = 3 * ugly[++index3];
            if (factor5 == min)
                factor5 = 5 * ugly[++index5];
        }
        return ugly[n - 1];
    }

}
