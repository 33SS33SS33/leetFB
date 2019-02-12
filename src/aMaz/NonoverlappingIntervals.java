package aMaz;

import java.util.PriorityQueue;

/**
 * Created by shanshan on 2/12/19.
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Input: [ [1,2], [1,2], [1,2] ]Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 */
public class NonoverlappingIntervals {

    public int nonoverlappingIntervals(Interval[] intervals) {
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        for (Interval i : intervals) pq.offer(i);
        int pre = Integer.MIN_VALUE, cnt = 0;
        while (pq.size() > 0) {
            Interval cur = pq.poll();
            if (cur.start >= pre) pre = cur.end;
            else cnt++;
        }
        return cnt;
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
    }
}
