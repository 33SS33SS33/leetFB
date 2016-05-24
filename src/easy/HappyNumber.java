package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */

/**按照规则构造即可 记录一下出现过的数*/
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappyA(32));
        System.out.println(new HappyNumber().isHappyB(32));
    }

    public boolean isHappyA(int n) {
        Set<Integer> s = new HashSet<Integer>();
        for (; ; ) {
            n = trans(n);
            if (n == 1)
                return true;
            if (s.contains(n))
                return false;
            s.add(n);
        }
    }

    int trans(int n) {
        int s = 0;
        do {
            int t = n % 10;
            s += t * t;
            n /= 10;
        } while (n > 0);
        return s;
    }

    public boolean isHappyB(int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        while (!set.contains(n)) {
            set.add(n);
            n = sum(getDigits(n));
            if (n == 1)
                return true;
        }
        return false;
    }

    public int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum = sum + i * i;
        }
        return sum;
    }

    public int[] getDigits(int n) {
        String s = String.valueOf(n);
        int[] result = new int[s.length()];
        int i = 0;

        while (n > 0) {
            int m = n % 10;
            result[i++] = m;
            n = n / 10;
        }
        return result;
    }
}
