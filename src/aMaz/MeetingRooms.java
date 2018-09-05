package aMaz;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],return false.
 * Input: [[7,10],[2,4]] Output: true
 * 一个人能不能参加所有会议
 * 按照start来排序 如果后一个的start 小于 前一个的end 就return False
 * 会议中间时间没有重叠的话就可以参加
 */
public class MeetingRooms {
    public static void main(String[] args) {
        Interval inter1 = new Interval(1, 4);
        Interval inter2 = new Interval(5, 6);
        Interval[] intervals = {inter1, inter2};
        System.out.println(new MeetingRooms().canAttendMeetingsa(intervals));
    }

    //最好的
    public boolean canAttendMeetingsa(Interval[] intervals) {
        if (intervals == null)
            return false;
        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i].start < intervals[i - 1].end)
                return false;
        return true;
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
