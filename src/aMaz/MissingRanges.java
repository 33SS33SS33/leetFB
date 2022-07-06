package aMaz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 */
public class MissingRanges {
    public static void main(String[] args) {
        int[] vals = {0, 1, 3, 50, 75};
        List<String> res1 = findMissingRanges1(vals, 0, 99);
        List<String> res = findMissingRanges(vals, 0, 99);
        for (String s : res1) {
            System.out.println(s);
        }
    }

    //TODO

    /**
     * Given a sorted integer array where the range of elements
     * are in the inclusive range [lower, upper], return its missing ranges.
     * given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     * 就是需要在数组里相减就行
     * 注意数组的预处理 比如在算两个边界的时候 要+1和-1 以及把边界插入进数组
     */
    public static List<String> findMissingRanges1(int[] A, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int pre = lower - 1;
        for (int i = 0; i <= A.length; i++) {
            int after = i == A.length ? upper + 1 : A[i];
            if (pre + 2 == after) {
                result.add(String.valueOf(pre + 1));
            } else if (pre + 2 < after) {
                result.add(String.valueOf(pre + 1) + "->" + String.valueOf(after - 1));
            }
            pre = after;
        }
        return result;
    }

    public static List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<>();
        int prev = start - 1;
        for (int i = 0; i <= vals.length; i++) {
            int curr = (i == vals.length) ? end + 1 : vals[i]; //??
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
