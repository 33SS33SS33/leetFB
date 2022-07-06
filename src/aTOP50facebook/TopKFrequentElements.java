package aTOP50facebook;

import java.util.*;

public class TopKFrequentElements {
    /**
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     */
    public static void main(String[] args) {
        int[] A = {1, 1, 1, 2, 2, 3};
        int[] res = new TopKFrequentElements().topKFrequent(A, 2);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }
        Map<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2));
        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }
        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
