package aTOP50Microsoft;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int meetingRoomsIIa(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a.end));
        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }
            // don't forget to put the meeting room back
            heap.offer(interval);
        }
        return heap.size();
    }

    public class Interval {
        public int start;
        public int end;

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
