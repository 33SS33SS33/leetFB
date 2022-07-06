package aTOP50facebook;

import java.util.PriorityQueue;

public class KthLargestElementinanArray {
    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     * Input: nums = [3,2,1,5,6,4], k = 2
     * Output: 5
     */
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
