package aMaz;

import java.util.*;

/**
 * Given n non-negative integers representing the histogram's bar height where HARD
 * the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height =[2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * Given height = [2,1,5,6,2,3],return 10. Tags: Array, Stack
 * 主要思路可以看geeksforgeeks
 * 首先 主体思路就是对于每个bar 我们都去求出以当前的bar为最低的bar的面积 然后所有这些面积的最大值就是结果
 * 在求以当前bar为最低bar的面积的时候 最难的就是要确定这个最低bar的左边界还有右边界
 * stack解法就是巧妙地解决了这个问题
 * 最重要的 stack里存的是索引 不是值
 * stack里存的的都是递增的序列 如果碰到小于栈顶的
 * 那么 就计算栈顶的元素的面积 这个元素的面积  左边界就是它自己  右边界就是这个小于它的元素
 * 然后弹出  然后如果栈顶的还是大 那么继续计算  因为存的是索引  所以宽度计算都是正确的
 */
class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleInHistogram(height));
        System.out.println(largestRectangleInHistogramb(height));
    }

    public static int largestRectangleInHistogram(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;  //????
            }
        }
        return maxArea;
    }

    /**
     * Only height is smaller do update happens
     * Stack for indices
     * add a zero height into the group
     */
    public static int largestRectangleInHistogramb(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        height = Arrays.copyOf(height, height.length + 1); // add a zero
        int max = 0;
        Stack<Integer> s = new Stack<Integer>(); // store indices
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[i] < height[s.peek()]) {//update when current height is smaller
                int h = height[s.pop()];
                int w = (s.isEmpty() ? i : i - s.peek() - 1);
                max = Math.max(max, h * w);
            }
            s.push(i); // push index into stack
        }
        return max;
    }

}