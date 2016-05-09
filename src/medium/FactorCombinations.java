package medium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 */

/**使用dfs 但是很慢  待优化 未实现*/
public class FactorCombinations {
    public static void main(String[] args) throws IOException {
        System.out.println(getFactors(33));
    }

    public static List<List<Integer>> getFactors(int n) {
        return getFactors(n, 2, n);
    }
    static List<List<Integer>> getFactors(int n, int low, int high) {
        List<List<Integer>> found = new ArrayList<List<Integer>>();
        if (low <= n && n < high) {
            found.add(Arrays.asList(n));
        }
        for (int i = low; n / i >= low; i++) {
            if (n % i == 0) {
                for (List<Integer> sub : getFactors(n / i, i, n)) {
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(i);
                    l.addAll(sub);
                    found.add(l);
                }
            }
        }
        return found;
    }

}
