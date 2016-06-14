package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18. Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. 整体思想是尝试把每一位固定为1然后看看有多少种组合
 * 整体思想是尝试把每一位固定为1然后看看有多少种组合
 * 首先把一位设置为1 然后取出1左边的高位  然后再取出1右边的低位
 * 然后求出一共有多少种组合  这里有一种情况就是如果当前位本来就是1 所以要在高位减1 然后低位加上尾部的数字  并没有太搞懂
 * 首先比如数字 223 然后假设指针到了中间的那个2 那么按照算法 前面应该是3 然后直接乘以10 就是30个 因为 正好是 010-019 110-119 210-219
 * 如果数字变成了213 那么就不能有210-219 所以前面变成了2 然后乘以10 加上4 就是24个 010-019 110-119 210-213
 * 最后如果是203 那么前面变成了2 而且尾部的也什么都没有了 因为中间位是0 所以是20个 010-019 110-119
 * /**整体思想是尝试把每一位固定为1然后看看有多少种组合
 * 首先把一位设置为1 然后取出1左边的高位  然后再取出1右边的低位
 * 然后求出一共有多少种组合  这里有一种情况就是如果当前位本来就是1 所以要在高位减1 然后低位加上尾部的数字  并没有太搞懂
 * 首先比如数字 223 然后假设指针到了中间的那个2 那么按照算法 前面应该是3 然后直接乘以10 就是30个 因为 正好是 010-019 110-119 210-219
 * 如果数字变成了213 那么就不能有210-219 所以前面变成了2 然后乘以10 加上4 就是24个 010-019 110-119 210-213
 * 最后如果是203 那么前面变成了2 而且尾部的也什么都没有了 因为中间位是0 所以是20个 010-019 110-119
 */

/**
 * 每次右移一位和1与
 */
public class NumberofDigitOne {
    public static void main(String[] args) {
        System.out.print(new NumberofDigitOne().countDigitOne(13));
    }

    // return { highest digit, floor(n) }
    // 'floor' here has a naming problem...
    // named it exactly later
    // floor(101)   = 100
    // floor(92)    = 90
    // floor(10111) = 10000
    int[]                 N     = { 100000000, 10000, 100, 10 };
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
