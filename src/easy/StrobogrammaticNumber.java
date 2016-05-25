package easy;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

/**
 * 一共就只有0 1 6 8 9这几个数组  然后这个还一定要是对称的
 * 所以一个指针从头 另一个指针是从尾部 然后每次比对这个单词 一直比对到中间就行了
 */
public class StrobogrammaticNumber {
    public static void main(String[] args) {
        StrobogrammaticNumber r = new StrobogrammaticNumber();
        String a = "8778";
        System.out.println(r.isStrobogrammatic(a));
    }

    static final char[][] GOOD_PATTERNS = { { '9', '6' }, { '6', '9' }, { '1', '1' }, { '8', '8' },
            { '0', '0' }, };

    public boolean isStrobogrammatic(String num) {
        char[] S = num.toCharArray();
        for (int i = 0; i <= S.length / 2; i++) {
            if (!isStrobogrammatic(S[i], S[S.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

    boolean isStrobogrammatic(char l, char r) {
        char[] s = new char[] { l, r };
        for (char[] g : GOOD_PATTERNS) {
            if (Arrays.equals(g, s)) {
                return true;
            }
        }
        return false;
    }
}
