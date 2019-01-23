package aMaz;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.HARD
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window.
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * Could you solve it in linear time?
 * 遍历数组nums，使用双端队列deque维护滑动窗口内有可能成为最大值元素的数组下标
 * 由于数组中的每一个元素至多只会入队、出队一次，因此均摊时间复杂度为O(n)
 * 记当前下标为i，则滑动窗口的有效下标范围为[i - (k - 1), i]
 * 若deque中的元素下标＜ i - (k - 1)，则将其从队头弹出，deque中的元素按照下标递增顺序从队尾入队。
 * 这样就确保deque中的数组下标范围为[i - (k - 1), i]，满足滑动窗口的要求。
 * 当下标i从队尾入队时，顺次弹出队列尾部不大于nums[i]的数组下标（这些被弹出的元素由于新元素的加入而变得没有意义）
 * deque的队头元素即为当前滑动窗口的最大值
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] A = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res1 = maxSlidingWindowa(A, 3);
//        int[] res = maxSlidingWindow(A, 3);
        for (int i : res1) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * best
     * We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.
     * At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means
     * If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array
     * Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i,
     * then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.
     * As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
     */
    public static int[] maxSlidingWindowa(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
/*
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] T = new int[Math.min(nums.length - k + 1, nums.length)];
        SlidingMaxQueue Q = new SlidingMaxQueue(nums, k);
        Q.add(0);
        for (int i = 1; i <= nums.length; i++) {
            T[Math.max(i - k, 0)] = Q.max();
            Q.add(i);
        }
        return T;
    }

    static class SlidingMaxQueue {
        int[] nums;
        int k;
        LinkedList<Integer> queue = new LinkedList<Integer>();

        SlidingMaxQueue(int[] nums, int k) {
            this.nums = nums;
            this.k = k;
        }

        int max() {
            return nums[queue.peekFirst()];
        }

        void add(int i) {
            if (i >= nums.length)
                return;
            // remove invalid index
            while (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            // remove nums < current;
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.add(i);
        }
    }*/

}
