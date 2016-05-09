package easy;

import java.util.BitSet;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
/**埃拉托斯特尼筛法*/
public class CountPrimes {
    public static void main(String[] args) {
        int num = -19;
        System.out.println(countPrimes(num));
        System.out.println(countPrimesB(num));
    }

    public static int countPrimes(int n) {
        if (n <= 2)
            return 0;
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        return count;
    }

    public static int countPrimesB(int n) {
        if (n < 2)
            return 0;
        BitSet b = new BitSet();
        b.set(0);
        b.set(1);
        for (int p = 2; p * 2 < n; p = b.nextClearBit(p + 1)) {
            for (int i = 2; p * i < n; i++) {
                b.set(p * i);
            }
        }
        b.flip(0, n);
        return b.cardinality();
    }

}
