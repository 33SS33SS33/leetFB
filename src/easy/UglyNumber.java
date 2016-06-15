package easy;

/**
 * Created by shanshan on 16/6/14.
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * <p/>
 * Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        for (int i = 2; i < 6 && num > 0; i++)
            while (num % i == 0)
                num /= i;
        return num == 1;
    }

    public boolean isUglya2(int num) {
        if (num == 1)
            return true;
        if (num == 0)
            return false;
        while (num % 2 == 0)
            num = num >> 1;
        while (num % 3 == 0)
            num = num / 3;
        while (num % 5 == 0)
            num = num / 5;
        return num == 1;
    }
}
