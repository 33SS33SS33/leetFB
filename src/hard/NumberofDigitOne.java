package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18. Given an integer n,
 * count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. 整体思想是尝试把每一位固定为1然后看看有多少种组合
 * 整体思想是尝试把每一位固定为1然后看看有多少种组合
 * 首先把一位设置为1 然后取出1左边的高位  然后再取出1右边的低位
 * 然后求出一共有多少种组合  这里有一种情况就是如果当前位本来就是1 所以要在高位减1 然后低位加上尾部的数字  并没有太搞懂
 * 首先比如数字 223 然后假设指针到了中间的那个2 那么按照算法 前面应该是3 然后直接乘以10 就是30个 因为 正好是 010-019 110-119 210-219
 * 如果数字变成了213 那么就不能有210-219 所以前面变成了2 然后乘以10 加上4 就是24个 010-019 110-119 210-213
 * 最后如果是203 那么前面变成了2 而且尾部的也什么都没有了 因为中间位是0 所以是20个 010-019 110-119
 * 每次右移一位和1与
 */
public class NumberofDigitOne {
    public static void main(String[] args) {
        System.out.println(new NumberofDigitOne().countDigitOnea(13));
    }

    /**
     * Go through the digit positions by using position multiplier m with values 1, 10, 100,
     * 1000, etc.
     * For each position, split the decimal representation into two parts, for example split
     * n=3141592 into a=31415 and b=92 when we're at m=100 for analyzing the
     * hundreds-digit. And then we know that the hundreds-digit of n is 1 for prefixes "" to
     * "3141", i.e., 3142 times. Each of those times is a streak, though. Because it's the
     * hundreds-digit, each streak is 100 long. So (a / 10 + 1) * 100 times, the
     * hundreds-digit is 1.
     *
     * Consider the thousands-digit, i.e., when m=1000. Then a=3141 and b=592. The
     * thousands-digit is 1 for prefixes "" to "314", so 315 times. And each time is a streak of
     * 1000 numbers. However, since the thousands-digit is a 1, the very last streak isn't
     * 1000 numbers but only 593 numbers, for the suffixes "000" to "592". So (a / 10 *
     * 1000) + (b + 1) times, the thousands-digit is 1.
     * The case distinction between the current digit/position being 0, 1 and >=2 can easily
     * be done in one expression. With (a + 8) / 10 you get the number of full streaks,
     * and a % 10 == 1 tells you whether to add a partial streak.
     */
    public int countDigitOnea(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        return ones;
    }

}
