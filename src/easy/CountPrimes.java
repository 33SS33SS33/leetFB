package easy;

import java.util.BitSet;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**Count the number of prime numbers less than a non-negative number, n.*/
public class CountPrimes {
    public static void main(String[] args) {
        int num=3;
        System.out.println(countPrimes(num));
    }
    public static int countPrimes(int n) {
        if(n < 2) return 0;
        BitSet b = new BitSet();
        b.set(0);
        b.set(1);

        for(int p = 2; p * 2 < n ; p = b.nextClearBit(p + 1)){
            for(int i = 2; p * i < n ;i++){
                b.set(p * i);
            }
        }
        b.flip(0, n);
        return b.cardinality();
    }

    /**creek This solution is the implementation of Sieve of Eratosthenes.*/
    public static int countPrimesB(int n) {
        if (n <= 2)
            return 0;

        // init an array to track prime numbers
        boolean[] primes = new boolean[n];
        for (int i = 2; i < n; i++)
            primes[i] = true;

        for (int i = 2; i <= Math.sqrt(n - 1); i++) {
            // or for (int i = 2; i <= n-1; i++) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i)
                    primes[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i])
                count++;
        }

        return count;
    }
}
