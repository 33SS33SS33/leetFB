package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 */

/**
 * 主要思路是比如求50-100范围的时候 先计算小于50的个数  再计算小于100的个数 然后用小于100的个数减去小于50的个数
 * 然后单独计算小于i的个数的时候 是直接用数学方法算出来  i的位数-1 的个数
 * 然后再算出来i的位数一共有多少个 然后从这些里面找出来小于i的 然后把两个个数加起来
 */
public class StrobogrammaticNumber3 {
    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber3().strobogrammaticInRange("50", "100"));
    }

    char[][] pairs = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };
    int      count = 0;

    public int strobogrammaticInRange(String low, String high) {
        for (int len = low.length(); len <= high.length(); len++) {
            dfs(low, high, new char[len], 0, len - 1);
        }
        return count;
    }

    public void dfs(String low, String high, char[] c, int left, int right) {
        if (left > right) {
            String s = new String(c);
            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length()
                    && s.compareTo(high) > 0))
                return;
            count++;
            return;
        }
        for (char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];
            if (c.length != 1 && c[0] == '0')
                continue;
            if (left < right || left == right && p[0] == p[1])
                dfs(low, high, c, left + 1, right - 1);
        }
    }
}
