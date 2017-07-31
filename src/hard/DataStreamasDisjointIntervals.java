package hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by GAOSHANSHAN835 on 2016/6/2.
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class DataStreamasDisjointIntervals {
    /**
     * Initialize your data structure here.
     */
    private TreeSet<Interval> intervalSet;

    public DataStreamasDisjointIntervals() {
        intervalSet = new TreeSet<Interval>(new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
    }

    public void addNum(int val) {
        Interval valInterval = new Interval(val, val);
        Interval floor = intervalSet.floor(valInterval);
        if (floor != null) {
            if (floor.end >= val) {
                return;
            } else if (floor.end + 1 == val) {
                valInterval.start = floor.start;
                intervalSet.remove(floor);
            }
        }
        Interval higher = intervalSet.higher(valInterval);
        if (higher != null && higher.start == val + 1) {
            valInterval.end = higher.end;
            intervalSet.remove(higher);
        }
        intervalSet.add(valInterval);
    }

    public List<Interval> getIntervals() {
        return Arrays.asList(intervalSet.toArray(new Interval[0]));
    }

    public static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

}
