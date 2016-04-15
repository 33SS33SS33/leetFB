package hard;

import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * 
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * Tags: Array, Sort
 */
/*和mergeIntervals差不多 但是要考虑更多的情况
因为插入的话  可能会new的那个区间会整个插入到当前区间的前面 而没有 merge
然后也会有merge的情况 而且merge的时候 还要考虑start的大小 不光考虑end
当merge完成 res的就不是空的了  之后只需要考虑intervals剩下的元素要不要和末尾的merge或者直接插入 这部分就和上题一样了
还有更简单的解法 未实现
可以先找出来不用merge的那些  然后分成左右两部分  剩下的merge了就可以*/
class InsertInterval {
    public static void main(String[] args) {
        Interval interval1=new Interval(1,3);
        Interval interval2=new Interval(6,9);
        Interval interval3=new Interval(2,5);
        List<Interval> intervals=new LinkedList<Interval>();
        intervals.add(interval1);
        intervals.add(interval2);
        List<Interval> res=new InsertInterval().insertA(intervals,interval3);
        List<Interval> res2=new InsertInterval().insertB(intervals,interval3);
        List<Interval> res3=new InsertInterval().insertC(intervals,interval3);
        List<Interval> res4=new InsertInterval().InsertInterval(intervals,interval3);
        System.out.print(res.toString());
        System.out.print(res2.toString());
        System.out.print(res3.toString());
        System.out.print(res4.toString());
    }

    /**--------creek-------!!!!*/
    public ArrayList<Interval> InsertInterval(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        for(Interval interval: intervals){
            if(interval.end < newInterval.start){
                result.add(interval);
            }else if(interval.start > newInterval.end){
                result.add(newInterval);
                newInterval = interval;
            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
                newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
            }
        }
        result.add(newInterval);
        return result;
    }

    /**非原地方法
     * O(n), not in place solution, make use of intervals are sorted
     * Go through the list, compare interval's start and end with the last 
     * interval of result, they may overlap
     */
    public List<Interval> insertA(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        res.add(newInterval);
        if (intervals == null || intervals.size() == 0) return res;
        for (Interval i : intervals) {
            int a = res.get(res.size() - 1).start;
            int b = res.get(res.size() - 1).end;
            if (i.end < a) res.add(res.size() - 1, i); // no overlap, add to second last
            else if (b < i.start) res.add(i); // no overlap, add to last
            else {
                a = Math.min(a, i.start);
                b = Math.max(b, i.end);
                res.set(res.size()- 1, new Interval(a, b));
            }
        }
        return res;
    }
    
    /** 原地方法
     * In place solution
     * Find start and end point of the interval to be merged
     */
    public List<Interval> insertB(List<Interval> intervals, Interval newInterval) {
        List<Interval> results = new ArrayList<Interval>();
        if (intervals == null||intervals.size() == 0){
            results.add(newInterval);
            return results;
        }
        // find position for new interval
        int start = 0, end = 0; // end points to the position after
        for (Interval i : intervals){
            if (newInterval.start > i.end) start++;
            if (newInterval.end >= i.start) end++;
            else break;
        }
        if (start == end) {
            results.addAll(intervals);
            results.add(start, newInterval);
            return results;
        }
        // add intervals from 0 to start
        for (int i = 0; i < start; i++) results.add(intervals.get(i));
        // build and add overlapped interval
        Interval interval = new Interval(Math.min(newInterval.start, intervals.get(start).start), Math.max(newInterval.end, intervals.get(end - 1).end)); // note that it's end - 1
        results.add(interval);
        // add remainning intervals
        for (int i = end; i < intervals.size(); i++) results.add(intervals.get(i));
        return results;
    }

    public ArrayList<Interval> insertC(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if(intervals.size()==0) {
            res.add(newInterval);
            return res;
        }
        int i=0;
        while(i<intervals.size() && intervals.get(i).end<newInterval.start) {
            res.add(intervals.get(i));
            i++;
        }
        if(i<intervals.size())
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        res.add(newInterval);
        while(i<intervals.size() && intervals.get(i).start<=newInterval.end) {
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        while(i<intervals.size()) {
            res.add(intervals.get(i));
            i++;
        }
        return res;
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        @Override public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
