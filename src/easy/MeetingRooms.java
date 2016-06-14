package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 * 一个人能不能参加所有会议按照start来排序 如果后一个的end 小于 前一个的start 就return False
 * 按照start来排序 如果后一个的end 小于 前一个的start 就return False
 * <p/>
 * 按照start来排序 如果后一个的end 小于 前一个的start 就return False
 * 会议中间时间没有重叠的话就可以参加
 */
public class MeetingRooms {
    public static void main(String[] args) {
        Interval inter1 = new Interval(1, 4);
        Interval inter2 = new Interval(5, 6);
        Interval[] intervals = { inter1, inter2 };
        System.out.println(new MeetingRooms().canAttendMeetings(intervals));
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        //Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int maxend = 0;
        for (Interval i : intervals) {
            if (i.start < maxend) {
                return false;
            }
            maxend = Math.max(maxend, i.end);
        }
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
