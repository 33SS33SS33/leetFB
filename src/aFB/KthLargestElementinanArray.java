package aFB;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 * Tags: Divide and Conquer, Heap
 * Similar Problems: (M) Wiggle Sort II
 * 使用快速选择算法 quick select
 * 算法有些类似快排 先选娶一个pivot 这里我选了数组第一个元素  然后把比pivot小的数放进一个数组  把比pivot大的数放进另外一个数组
 * 结束后开始和k比较  首先如果k比那个存big的数组的长度要小  就说明k落在这个数组里
 * 然后 如果k比数组总长减去存small数组长度要大  则说明k落在small的数组里  最后 如果都没有 则说明k是pivot 返回
 * 也可以用快排的partition来做 类似算法课上的办法 partition过后就知道pivot是在第几个位置 然后拿k和他的索引比  比他小就在它左边 比他大就在他右边 要么就是这个位置
 * 需要练习手写快排 未实现
 * 第二遍small的if写错了  重要
 */
class KthLargestElementinanArray {
    public static void main(String[] args) {
        KthLargestElementinanArray K = new KthLargestElementinanArray();
        int[] A = {1, 23, 12, 9, 30, 2, 50};
        System.out.println(K.findKthLargest1(A, 3));
        System.out.println(K.findKthLargesta(A, 3));
        System.out.println(K.findKthLargest(A, 3));
        System.out.println(K.findKthLargestc(A, 3));
    }

    //多种解法
    //https://discuss.leetcode.com/topic/14597/solution-explained/2
    //O(N lg N) running time + O(1) memory
    public int findKthLargest1(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }

    /**
     * O(N lg K) running time + O(K) memory
     */
    public int findKthLargesta(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * Priority Queue
     * O(n) + k * O(logn)
     */
    public int findKthLargest(int[] A, int k) {
        if (k <= 0 || k > A.length)
            return -1;
        Queue<Integer> q = new PriorityQueue<Integer>(A.length, Collections.reverseOrder());
        for (int n : A)
            q.add(n);
        int res = 0;
        for (int i = 0; i < k; i++)
            res = q.poll();
        return res;
    }

    //O(N) best case / O(N^2) worst case running time + O(1) memory
    public int findKthLargestc(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (i < hi && less(a[++i], a[lo])) ;
            while (j > lo && less(a[lo], a[--j])) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

}
