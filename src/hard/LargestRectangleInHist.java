package hard;

import java.util.*;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height =[2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * Tags: Array, Stack
 */

/**
 * 主要思路可以看geeksforgeeks
 * 首先 主体思路就是对于每个bar 我们都去求出以当前的bar为最低的bar的面积 然后所有这些面积的最大值就是结果
 * 在求以当前bar为最低bar的面积的时候 最难的就是要确定这个最低bar的左边界还有右边界
 * stack解法就是巧妙地解决了这个问题
 * 最重要的  stack里存的是索引 不是值
 * stack里存的的都是递增的序列 如果碰到小于栈顶的  那么 就计算栈顶的元素的面积 这个元素的面积  左边界就是它自己  右边界就是这个小于它的元素
 * 然后弹出  然后如果栈顶的还是大 那么继续计算  因为存的是索引  所以宽度计算都是正确的
 */
class LargestRectangleInHist {
    public static void main(String[] args) {
        int[] height = { 2, 1, 5, 6, 2, 3 };
        int[] height2 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(new LargestRectangleInHist().largestRectangleArea(height));
        System.out.println(new LargestRectangleInHist().largestRectangleAreaB(height));
        System.out.println(new LargestRectangleInHist().largestRectangleAreaC(height));
        System.out.println(new LargestRectangleInHist().largestRectangleAreaD(height));
    }

    /**
     * Only height is smaller do update happens
     * Stack for indices
     * add a zero height into the group
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        height = Arrays.copyOf(height, height.length + 1); // add a zero
        int max = 0;
        Stack<Integer> s = new Stack<Integer>(); // store indices
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[i] < height[s.peek()]) { // update when current height is smaller
                int h = height[s.pop()];
                int w = (s.isEmpty() ? i : i - s.peek() - 1);
                max = Math.max(max, h * w);
            }
            s.push(i); // push index into stack
        }
        return max;
    }

    public int largestRectangleAreaB(int[] height) {
        if(height.length == 0) return 0;
        height = Arrays.copyOf(height, height.length + 1);
        height[height.length - 1] = 0;
        Deque<Rect> stack = new LinkedList<Rect>();
        stack.push(new Rect(height[0]));
        int max = 0;
        next:
        for(int i = 1; i < height.length; i++){
            int h = height[i];
            Rect r = new Rect(h);
            int sl = 0;
            while(true){
                if(stack.isEmpty() || h > stack.peek().height){
                    stack.push(r);
                    continue next;
                }
                Rect left = stack.pop();
                sl += left.width;
                max = Math.max(max, left.height * sl);
                r.width = 1 + sl ; // merge left into new
            }
        }
        return max;
    }

    public int largestRectangleAreaC(int[] height) {
        if(height==null || height.length==0)
            return 0;
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0;i<height.length;i++) {
            while(!stack.isEmpty() && height[i]<=height[stack.peek()]) {
                int index = stack.pop();
                int curArea = stack.isEmpty()?i*height[index]:(i-stack.peek()-1)*height[index];
                max = Math.max(max,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int index = stack.pop();
            int curArea = stack.isEmpty()?height.length*height[index]:(height.length-stack.peek()-1)*height[index];
            max = Math.max(max,curArea);
        }
        return max;
    }

    static class Rect {
        int width = 1;
        int height;
        Rect(int height){
            this.height = height;
        }
    }

    /**creek----*/
    public int largestRectangleAreaD(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;
        while (i < height.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = height[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }
        }
        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = height[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }
        return max;
    }
}