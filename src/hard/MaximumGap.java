package hard;

import java.util.*;

/**
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit
 * in the 32-bit signed integer range.
 * Tags: Sort
 * 重要 注意size等于0的时候的处理
 * 使用了桶排序 桶排序最困难的地方就是在于确定桶的大小以及一共应该有多少个桶
 * 假设有N个元素A到B。
 * 那么最大差值不会小于ceiling[(B - A) / (N - 1)]
 * 令bucket（桶）的大小len = ceiling[(B - A) / (N - 1)]，则最多会有(B - A) / len + 1个桶
 * 对于数组中的任意整数K，很容易通过算式loc = (K - A) / len找出其桶的位置，然后维护每一个桶的最大值和最小值
 * 由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。
 * 对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回所有这些可能值中的最大值。
 * 还可以用基数排序 未实现
 */
class MaximumGap {
    public static void main(String[] args) {
        MaximumGap mg = new MaximumGap();
        System.out.println(mg.maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(mg.maximumGapB(new int[]{3, 6, 9, 1}));
        System.out.println(mg.maximumGapC(new int[]{3, 6, 9, 1}));
    }

    /**
     * O(n) Time, O(n) Space 最好的
     * Find max and min in one traverse
     * Calculate bucket length and divide numbers into buckets
     * Traverse buckets to find max gap
     */
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        int n = num.length;
        /*find max and min value*/
        int min = num[0];
        int max = num[0];
        for (int i : num) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        /*put into buckets*/
        double dist = (double) (max - min) / (n - 1);
        int[] uppers = new int[n - 1];
        int[] lowers = new int[n - 1];
        Arrays.fill(uppers, -1);
        Arrays.fill(lowers, -1);
        for (int i : num) {
            int idx = (i == max ? n - 2 : (int) ((i - min) / dist));
            if (lowers[idx] == -1 || i < lowers[idx])
                lowers[idx] = i;
            if (uppers[idx] == -1 || i > uppers[idx])
                uppers[idx] = i;
        }
        /*find max gap*/
        int prevUpper = uppers[0]; // previous bucket can be skipped
        int maxGap = uppers[0] - lowers[0];
        for (int i = 1; i < n - 1; i++) {
            if (lowers[i] == -1)
                continue; // no min in this bucket
            maxGap = Math.max(maxGap, lowers[i] - prevUpper);
            prevUpper = uppers[i];
        }
        return maxGap;
    }

    public int maximumGapB(int[] num) {
        if (num.length < 2)
            return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }
        int gap = (int) Math.ceil((double) (max - min) / (num.length - 1));
        int n = (max - min) / gap + 1;
        Bucket[] buckets = new Bucket[n];
        for (int i = 0; i < num.length; i++) {
            int index = (num[i] - min) / gap;
            if (buckets[index] == null)
                buckets[index] = new Bucket();
            buckets[index].add(num[i]);
        }
        int maxGap = Integer.MIN_VALUE;
        int prev = min;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null)
                continue;
            maxGap = Math.max(maxGap, buckets[i].min - prev);
            prev = buckets[i].max;
        }
        return maxGap;
    }

    static class Bucket {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        void add(int n) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
    }

    /**
     * creek---
     * The key part is to get the interval:
     * From: interval * (num[i] - min) = 0 and interval * (max -num[i]) = n
     * interval = num.length / (max - min)
     */
    public int maximumGapC(int[] num) {
        if (num == null || num.length < 2) {
            return 0;
        }
        int max = num[0];
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }
        // initialize an array of buckets
        Bucket2[] buckets = new Bucket2[num.length + 1]; //project to (0 - n)
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket2();
        }
        double interval = (double) num.length / (max - min);
        //distribute every number to a bucket array
        for (int i = 0; i < num.length; i++) {
            int index = (int) ((num[i] - min) * interval);
            if (buckets[index].low == -1) {
                buckets[index].low = num[i];
                buckets[index].high = num[i];
            } else {
                buckets[index].low = Math.min(buckets[index].low, num[i]);
                buckets[index].high = Math.max(buckets[index].high, num[i]);
            }
        }
        //scan buckets to find maximum gap
        int result = 0;
        int prev = buckets[0].high;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].low != -1) {
                result = Math.max(result, buckets[i].low - prev);
                prev = buckets[i].high;
            }
        }
        return result;
    }

    class Bucket2 {
        int low;
        int high;

        public Bucket2() {
            low = -1;
            high = -1;
        }
    }

}
