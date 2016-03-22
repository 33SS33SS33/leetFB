package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 */
public class MissingRanges {

    public static void main(String[] args) {
        int[] vals = { 1, 2, 3, 5 };
        List<String> res = findMissingRanges(vals, 0, 4);
        for (String s : res) {
            System.out.print(s);
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
