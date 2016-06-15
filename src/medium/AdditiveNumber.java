package medium;

import java.math.BigInteger;

/**
 * Created by shanshan on 16/5/9.
 * "Additive number is a positive integer whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 * each subsequent number in the sequence must be the sum of the preceding two.
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * Given a string represents an integer, write a function to determine if it's an additive number.
 * Follow up:
 * How would you handle overflow for very large input integers?"
 * <p/>
 * "使用DFS
 * For循环 切割字符串 每次就切出来一个 添加进path 然后再切割一个
 * if确保切出来的数字没有0开头的 比如02 03
 * 由于题目要求是有两个起始数字 然后后面的数字都得是前面两个数字之和
 * 所以当path为2的时候 就开始判断后面的部分符不符合题目条件
 * 判断过程就是首先取两个数字 然后按着这两个数字之和的位数 去切割剩下的字符串 然后判断这个切下来的数字等不等于刚才两个数字之和
 * 等于的话就把两个数字向后移动 并且继续判断
 * 不等于就返回 False"
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j) {
                if (num.charAt(i) == '0' && j > 1)
                    break;
                BigInteger x2 = new BigInteger(num.substring(i, i + j));
                if (isValid(x1, x2, j + i, num))
                    return true;
            }
        }
        return false;
    }

    private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
        if (start == num.length())
            return true;
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, start + sum.length(), num);
    }
}
