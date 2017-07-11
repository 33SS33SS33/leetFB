package aFB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * Tags: Array, Sort
 * 和mergeIntervals差不多 但是要考虑更多的情况
 * 因为插入的话  可能会new的那个区间会整个插入到当前区间的前面 而没有 merge
 * 然后也会有merge的情况 而且merge的时候 还要考虑start的大小 不光考虑end
 * 当merge完成 res的就不是空的了  之后只需要考虑intervals剩下的元素要不要和末尾的merge或者直接插入 这部分就和上题一样了
 * 还有更简单的解法 未实现
 * 可以先找出来不用merge的那些  然后分成左右两部分  剩下的merge了就可以
 */
class InsertInterval {
    public static void main(String[] args) {
        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(6, 9);
        Interval interval3 = new Interval(2, 5);
        List<Interval> intervals = new LinkedList<Interval>();
        intervals.add(interval1);
        intervals.add(interval2);
        List<Interval> ress = new InsertInterval().InsertIntervalS(intervals, interval3);
        List<Interval> res = new InsertInterval().insertA(intervals, interval3);
        System.out.print(ress.toString());
        System.out.print(res.toString());
    }

    /**
     * -!!!!最好的
     */
    public List<Interval> InsertIntervalS(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || newInterval == null) {
            return intervals;
        }
        ArrayList<Interval> result = new ArrayList<Interval>();
        int insertPos = 0;
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                result.add(interval);
            } else {
                newInterval = new Interval(Math.min(interval.start, newInterval.start),
                        Math.max(newInterval.end, interval.end));
            }
        }
        result.add(insertPos, newInterval);
        return result;
    }

    /**
     * 非原地方法 和最后一个元素比较
     * O(n), not in place solution, make use of intervals are sorted
     * Go through the list, compare interval's start and end with the last
     * interval of result, they may overlap
     */
    public List<Interval> insertA(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        res.add(newInterval);
        if (intervals == null || intervals.size() == 0)
            return res;
        for (Interval i : intervals) {
            int a = res.get(res.size() - 1).start;
            int b = res.get(res.size() - 1).end;
            if (i.end < a)
                res.add(res.size() - 1, i); // no overlap, add to second last
            else if (b < i.start)
                res.add(i); // no overlap, add to last
            else {
                a = Math.min(a, i.start);
                b = Math.max(b, i.end);
                res.set(res.size() - 1, new Interval(a, b));
            }
        }
        return res;
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

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
