package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 * <p/>
 * 最多需要多少房子
 * 一个数组是start排序 一个数组是end 排序  然后就按照最基本的逻辑开房间的逻辑即可
 * 就是在开房间之前检查有几个房间已经end了 end了 就是avail +1 然后用一间房 就avail-1
 * 如果没有房间avail 那就说明要开信访件 那就res +1
 * 还可以用堆来做 未实现
 */

//P705
public class MeetingRooms2 {
    public static void main(String[] args) {
        Interval inter1 = new Interval(1, 4);
        Interval inter2 = new Interval(2, 6);
        Interval inter3 = new Interval(4, 6);
        Interval[] intervals = { inter1, inter2, inter3 };
        System.out.println(new MeetingRooms2().minMeetingRooms(intervals));
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
        List<Interval> rooms       = new ArrayList<Interval>();
        int            currentTime = -1;

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
