package hard;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by shanshan on 16/5/9.
 * "Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far."
 * <p/>
 * "设计题 重要  一个heap不行可以用两个heap
 * 一个heap存当前数组比较小那一半的数 另一个heap存当前数组比较大那一半的数
 * 最关键的是addnumber时候的操作 每次都要均衡两个数组 让两个数组的数量和顺序都均衡 用到了pushpop
 * 主要看代码理解
 * if len(self.small) == len(self.large):
 * heappush(self.large, -heappushpop(self.small, -num))
 * else:
 * heappush(self.small, -heappushpop(self.large, num))"
 */

public class FindMedianfromDataStream {

    /**
     * 最好的
     */
    private Queue<Long> small = new PriorityQueue(), large = new PriorityQueue();

    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (large.size() < small.size())
            large.add(-small.poll());
    }

    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
    }

/*    Queue<Integer> q = new PriorityQueue(), z = q, t,
            Q = new PriorityQueue(Collections.reverseOrder());
    public void addNum(int num) {
        (t=Q).add(num);
        (Q=q).add((q=t).poll());
    }
    public double findMedian() {
        return (Q.peek() + z.peek()) / 2.;
    }*/
}
