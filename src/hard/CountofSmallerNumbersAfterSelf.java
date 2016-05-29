package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * Example:
 * Given nums = [5, 2, 6, 1]
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0]."
 */

/**
 * "这道题有好多解法 树状数组 线段树 归并排序 待学习 未实现
 * 线段树 还有树状数组一定要学习 重要
 * 相似题目 Range Sum Query - Mutable
 * 这里用了BST 主要是加入了自身的节点数 以及左孩子的个数的属性 但是很慢
 * 这种叫做逆序数 一般都可以用merge sort来解决  待学习"
 * "http://www.hawstein.com/posts/binary-indexed-trees.html
 * http://bookshadow.com/weblog/2015/12/06/leetcode-count-of-smaller-numbers-after-self/
 * https://leetcode.com/discuss/73256/mergesort-solution
 * http://www.cnblogs.com/TenosDoIt/p/3453089.html
 * https://leetcode.com/discuss/73509/nlogn-time-space-mergesort-solution-with-detail-explanation
 * http://www.geeksforgeeks.org/counting-inversions/
 * http://www.hrwhisper.me/leetcode-range-sum-query-mutable/
 * http://www.cnblogs.com/zichi/p/4806998.html"
 */
public class CountofSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        CountofSmallerNumbersAfterSelf r = new CountofSmallerNumbersAfterSelf();
        int[] nums = { 5, 2, 6, 1 };
        System.out.println(r.countSmaller(nums).toString());
    }

    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }
        return Arrays.asList(ans);
    }

    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0)
            return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target)
            return end + 1;
        if (sorted.get(start) >= target)
            return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (sorted.get(start) >= target)
            return start;
        return end;
    }
}
