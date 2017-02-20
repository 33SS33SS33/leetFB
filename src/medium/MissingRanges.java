package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 * <p>
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * <p>
 * 就是需要在数组里相减就行
 * 注意数组的预处理 比如在算两个边界的时候 要+1和-1 以及把边界插入进数组
 */
public class MissingRanges {
    public static void main(String[] args) {
        int[] vals = { 0, 1, 3, 50, 75};
        List<String> res = findMissingRanges(vals, 0, 99);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<String>();
        int prev = start - 1;
        for (int i = 0; i <= vals.length; i++) {
            int curr = (i == vals.length) ? end + 1 : vals[i];
            if (curr - prev >= 2) {
                ranges.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return ranges;
    }

    private static String getRange(int from, int to) {
        return (from == to) ? String.valueOf(from) : from + "->" + to;
    }
}
