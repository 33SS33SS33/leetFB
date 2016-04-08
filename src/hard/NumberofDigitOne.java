package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class NumberofDigitOne {
    public static void main(String[] args) {
            System.out.print(new NumberofDigitOne().countDigitOne(3));
    }
    // return { highest digit, floor(n) }
    // 'floor' here has a naming problem...
    // named it exactly later
    // floor(101)   = 100
    // floor(92)    = 90
    // floor(10111) = 10000
    int[] N = { 100000000, 10000, 100, 10 };
    Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
    public int countDigitOne(int n) {
        if (n <= 0)
            return 0;
        if (n < 10)
            return 1;
        Integer cached = cache.get(n);
        if (cached != null) {
            return cached;
        }
        int[] e = extractHighest(n);
        int h = e[0];
        int f = e[1];
        int rest = n - f;
        int plus = 0;
        if (h == 1) {
            plus = rest + 1;
        }
        int c = plus + countDigitOne(f - 1) + countDigitOne(rest);
        cache.put(n, c);
        return c;
    }
    int[] extractHighest(int x) {
        int e = 1;
        for (int n : N) {
            if (x >= n) {
                x /= n;
                e *= n;
            }
        }
        return new int[] { x, x * e };
    }
}
