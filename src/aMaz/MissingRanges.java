package aMaz;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public static void main(String[] args) {
        int[] vals = {0, 1, 3, 50, 75};
        List<String> res1 = new MissingRanges().findMissingRanges(vals, 0, 99);
        for (String s : res1) {
            System.out.println(s);
        }
    }

    /**
     * Given a sorted integer array where the range of elements
     * are in the inclusive range [lower, upper], return its missing ranges.
     * given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     * 就是需要在数组里相减就行
     * 注意数组的预处理 比如在算两个边界的时候 要+1和-1 以及把边界插入进数组
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if (prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    private String formatRange(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        }
        return lower + "->" + upper;
    }
}
