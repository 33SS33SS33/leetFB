package aFB;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * Tags: Array, Sort
 * <p/>
 * 首先 将interval数组以start为key排序  然后添加第一个进res  然后遍历interval
 * 每次先检查当前这个interval的start有没有大于res的end  大于的话就直接插入  不大于的话 就说明和之前有区间重合
 * merge即可
 * 但是要 注意 res最后是[1,4] 然后要插入[2,3]这种情况 所以用了 max
 */

class MergeIntervals {
    public static void main(String[] args) {
        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(6, 9);
        Interval interval3 = new Interval(2, 5);
        List<Interval> intervals = new LinkedList<Interval>();
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);
        ArrayList<Interval> intervals2 = new ArrayList<Interval>();
        intervals2.add(interval1);
        intervals2.add(interval2);
        intervals2.add(interval3);
        List<Interval> res = new MergeIntervals().MergeIntervalsA(intervals);
        System.out.print(res.toString());
        ArrayList<Interval> res2 = new MergeIntervals().MergeIntervalsB(intervals2);
        System.out.print(res2.toString());
    }

    /**
     * Sort and merge, O(nlogn)
     * Sort the intervals according to their start value
     * Go through the intervals and update last interval
     * If last interval in result overlap with current interval
     * Remove last interval and add new interval with updated end value
     * Which is the bigger of last.end and i.end
     */
    public List<Interval> MergeIntervalsA(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0)
            return res;
        Collections.sort(intervals, new MyComparator());
        for (Interval i : intervals) {
            if (res.isEmpty())
                res.add(i); // first interval
            else {
                Interval last = res.get(res.size() - 1); // get last interval
                if (last.end >= i.start) { // overlap
                    res.remove(last);
                    res.add(new Interval(last.start, Math.max(last.end, i.end))); // extend end
                } else
                    res.add(i); //no overlap
            }
        }
        return res;
    }

    /**
     * Comparator for interval
     * Sort according to start date
     */
    class MyComparator implements Comparator<Interval> {
        @Override public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
    }

    /**
     * ----creek---
     */
    public ArrayList<Interval> MergeIntervalsB(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        // sort intervals by using self-defined Comparator
        Collections.sort(intervals, new MyComparator());
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (prev.end >= curr.start) {
                // merged case
                Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = merged;
            } else {
                result.add(prev);
                prev = curr;
            }
        }
        result.add(prev);
        return result;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
