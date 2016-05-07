package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**最多需要多少房子
 * */
public class MeetingRooms2 {
    public static void main(String[] args) {
        Interval inter1=new Interval(1,4);
        Interval inter2=new Interval(2,6);
        Interval inter3=new Interval(4,6);
        Interval[] intervals = {inter1,inter2,inter3};
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
