package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 按照规则来就行
 * 记得for的时候最后要多出来一次循环 这样才能把最后一个range给加进去
 * 现有办法是先把range都记录在一个数组里
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges r = new SummaryRanges();
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(r.summaryRangesa(nums));
        System.out.println(r.summaryRanges(nums));
        System.out.println(r.summaryRangesB(nums));
    }

    public List<String> summaryRangesa(int[] nums) {
        List<String> list = new ArrayList();
        if (nums.length == 1) {
            list.add(nums[0] + "");
            return list;
        }
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
                i++;
            }
            if (a != nums[i]) {
                list.add(a + "->" + nums[i]);
            } else {
                list.add(a + "");
            }
        }
        return list;
    }

    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> rt = new ArrayList<String>();
        if (nums.length == 0)
            return rt;
        Range r = new Range(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - r.ed == 1) {
                r.ed = nums[i];
            } else {
                rt.add(r.toString());
                r = new Range(nums[i]);
            }
        }
        rt.add(r.toString());
        return rt;
    }

    static class Range {
        int st;
        int ed;

        Range(int st) {
            this.st = st;
            this.ed = st;
        }

        public String toString() {
            if (ed == st)
                return "" + st;
            return st + "->" + ed;
        }
    }

    /**
     * creek-------!!
     */
    public List<String> summaryRangesB(int[] nums) {
        List<String> result = new ArrayList<String>();
        if (nums == null || nums.length == 0)
            return result;
        if (nums.length == 1) {
            result.add(nums[0] + "");
        }
        int pre = nums[0]; // previous element
        int first = pre; // first element of each range
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre + 1) {
                if (i == nums.length - 1) {
                    result.add(first + "->" + nums[i]);
                }
            } else {
                if (first == pre) {
                    result.add(first + "");
                } else {
                    result.add(first + "->" + pre);
                }
                if (i == nums.length - 1) {
                    result.add(nums[i] + "");
                }
                first = nums[i];
            }
            pre = nums[i];
        }
        return result;
    }

}
