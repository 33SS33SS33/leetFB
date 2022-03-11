package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 按照规则构造即可 记录一下出现过的数
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappya(32));
        System.out.println(happyNumber(32));
    }

    /**
     * The idea is to use one hash set to record sum of every digit square of every number
     * occurred. Once the current sum cannot be added to set, return false; once the
     * current sum equals 1, return true;
     */

    /**
     * A happy number is a number defined by the following process: Starting with any positive integer,
     * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.Those numbers for which this process ends in 1 are happy numbers.
     * Example: 19 is a happy number
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static boolean happyNumber(int n) {
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

    public static boolean isHappya(int n) {
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

    public static int cal(int n) {
        int x = n;
        int s = 0;
        while (x > 0) {
            s = s + (x % 10) * (x % 10);
            x = x / 10;
        }
        return s;
    }
}
