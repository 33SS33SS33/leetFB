package aTOP50facebook;

import java.util.PriorityQueue;

public class KthLargestElementinanArray {
    public int kthLargestElementinanArray(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
