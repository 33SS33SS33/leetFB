package hard;

import java.util.LinkedList;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**
 * For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].*/
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] A = {1,3,-1,-3,5,3,6,7};
        int[] res=maxSlidingWindow(A,3);
        for(int i: res) {
            System.out.println(i);
        }
    }

    static class SlidingMaxQueue {
        int[] nums;
        int k;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        SlidingMaxQueue(int[] nums, int k){
            this.nums = nums;
            this.k = k;
        }

        int max(){
            return nums[queue.peekFirst()];
        }
        void add(int i){
            if(i >= nums.length) return;
            // remove invalid index
            while(!queue.isEmpty() && queue.peekFirst() <= i - k){
                queue.pollFirst();
            }
            // remove nums < current;
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                queue.pollLast();
            }
            queue.add(i);
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] T = new int[Math.min(nums.length - k + 1, nums.length)];
        SlidingMaxQueue Q = new SlidingMaxQueue(nums, k);
        Q.add(0);
        for(int i = 1; i <= nums.length; i++){
            T[Math.max(i - k, 0)] = Q.max();
            Q.add(i);
        }
        return T;
    }
}
