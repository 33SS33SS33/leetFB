package aMaz;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * 埃拉托斯特尼筛法
 */
public class CountPrimes {
    public static void main(String[] args) {
        int num = 19;
        System.out.println(countPrimes(10));
        System.out.println(countPrimes(num));
    }

    /**
     * Count the number of prime numbers less than a non-negative number, n.
     */
    public static int countPrimes(int n) {
        if (n <= 2)
            return 0;
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }
}
