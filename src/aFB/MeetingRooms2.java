package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 * 最多需要多少房子
 * 一个数组是start排序 一个数组是end 排序  然后就按照最基本的逻辑开房间的逻辑即可
 * 就是在开房间之前检查有几个房间已经end了 end了 就是avail +1 然后用一间房 就avail-1
 * 如果没有房间avail 那就说明要开新房间 那就res +1
 * 还可以用堆来做 未实现
 */

//P705
public class MeetingRooms2 {
    public static void main(String[] args) {
        Interval inter1 = new Interval(1, 4);
        Interval inter2 = new Interval(2, 6);
        Interval inter3 = new Interval(4, 6);
        Interval[] intervals = {inter1, inter2, inter3};
        System.out.println(minMeetingRooms1(intervals));
    }

    /**
     * 最好的  uses min heap, average time complexity is O(nlogn).
     * Sort the intervals by start time
     * Use a min heap to track the minimum end time of merged intervals
     * start with the first meeting, put it to a meeting room
     * get the meeting room that finishes earliest
     * if the current meeting starts right after
     * there's no need for a new room, merge the interval
     * otherwise, this meeting needs a new room
     * don't forget to put the meeting room back
     */
    public static int minMeetingRooms1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {

                interval.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
        return heap.size();
    }

    public int minMeetingRooms(Interval[] intervals) {
        //        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        RoomAllocator ra = new RoomAllocator();
        for (Interval i : intervals) {
            ra.freeBefore(i.start);
            ra.alloc(i);
        }
        return ra.rooms.size();
    }

    static class RoomAllocator {
        List<Interval> rooms = new ArrayList<Interval>();
        int currentTime = -1;

        void alloc(Interval room) {
            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i).end <= currentTime) {
                    rooms.set(i, room);
                    return;
                }
            }
            rooms.add(room);
        }

        void freeBefore(int time) {
            currentTime = time;
        }
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
