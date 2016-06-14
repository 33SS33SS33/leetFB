package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees
 * (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 * <p/>
 * 用dfs就行 分奇数和偶数两种情况 先插入最中间的会比较方便(因为每次插入两边的代码好写)
 */
public class StrobogrammaticNumber2 {
    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber2().findStrobogrammatic(2));
    }

    /**
     * 最好的
     */
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0)
            return new ArrayList<String>(Arrays.asList(""));
        if (n == 1)
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (n != m)
                res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}
