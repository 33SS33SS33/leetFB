package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 按照规则构造即可 记录一下出现过的数
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappya(32));
        System.out.println(new HappyNumber().isHappyb(32));
        System.out.println(new HappyNumber().isHappyA(32));
        System.out.println(new HappyNumber().isHappyB(32));
    }

    public boolean isHappya(int n) {
        int x = n;
        int y = n;
        while (x > 1) {
            x = cal(x);
            if (x == 1)
                return true;
            y = cal(cal(y));
            if (y == 1)
                return true;
            if (x == y)
                return false;
        }
        return true;
    }

    public int cal(int n) {
        int x = n;
        int s = 0;
        while (x > 0) {
            s = s + (x % 10) * (x % 10);
            x = x / 10;
        }
        return s;
    }

    /**
     * The idea is to use one hash set to record sum of every digit square of every number
     * occurred. Once the current sum cannot be added to set, return false; once the
     * current sum equals 1, return true;
     */
    public boolean isHappyb(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;
        }
        return false;
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
