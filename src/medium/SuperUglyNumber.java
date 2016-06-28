package medium;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/4/8.
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * Analysis:
 * similar to ugly number 2, DP
 * d[i] = Math.min(min, d[i-1]*primes[j])
 * example:   6 [2,3,7]
 * 2*[1],3*[1],7*[1] compare 2,3,7
 * [1,2]->2*[2],3*[1,2],7*[1,2] compare [4,3,7]
 * ....
 * Time O(n*(primes.length))
 * 直接不停的除以2 3 5 就行 要排除0 所以才设定了<num的条件
 * 还是之前那个题的思路
 * 这次用一个数组存储对应的prime的系数的指针
 * 有点慢  可以用heap来优化 未实现
 */
public class SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] d = new int[n];
        d[0] = 1;
        int[] p = new int[primes.length];
        Arrays.fill(p, 0);
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * d[p[j]]);
            }
            d[i] = min;
            for (int k = 0; k < primes.length; k++) {
                if (min == primes[k] * d[p[k]]) {
                    p[k]++;
                }
            }
        }
        return d[n - 1];
    }
}
