package hard;

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
        System.out.println(mg.maximumGapB(new int[]{3, 6, 9, 1}));
    }

    //最好的
    public int maximumGapB(int[] num) {
        if (num == null || num.length < 2)
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
     * creek ??
     * The key part is to get the interval:
     * From: interval * (num[i] - min) = 0 and interval * (max -num[i]) = n
     * interval = num.length / (max - min)
     */

}
