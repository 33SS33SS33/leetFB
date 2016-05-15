package hard;

import java.util.*;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * 
 * Tags: Array, Hashtable, Stack, DP
 */
class MaxRectangle {
    public static void main(String[] args) {
        char[][] matrix={{'1','1','1'},{'1','1','0'},{'1','1','0'}};
        System.out.println(new MaxRectangle().maximalRectangleA(matrix));
        System.out.println(new MaxRectangle().maximalRectangleB(matrix));
        System.out.println(new MaxRectangle().maximalRectangleC(matrix));
    }
    /*假设我们把矩阵沿着某一行切下来，然后把切的行作为底面，将自底面往上的矩阵看成一个直方图（histogram）。
    直方图的中每个项的高度就是从底面行开始往上1的数量。根据Largest Rectangle in Histogram我们就可以求出当前行作为矩阵下边缘的一个最大矩阵。
    接下来如果对每一行都做一次Largest Rectangle in Histogram，
    从其中选出最大的矩阵，那么它就是整个矩阵中面积最大的子矩阵。
    所以完成对一行为底边的矩阵求解复杂度是O(n+n)=O(n)。接下来对每一行都做一次，那么算法总时间复杂度是O(m*n)*/
    /*
     * row by row
     * create a height integer array to bigger than column size
     * set last height to zero(out of bounds)
     * build new height on each row
     * use stack to store indices
     * update area according to largest rectangle in histogram
     */
    public int maximalRectangleA(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int j = 0; j < n + 1; j++) {
                if (j < n) { // build height
                    if (matrix[i][j] == '1') height[j] += 1;
                    else height[j] = 0;
                }
                while (!s.isEmpty() && height[j] < height[s.peek()]) {
                    int h = height[s.pop()];
                    int w = (s.isEmpty() ? j : j - s.peek() - 1);
                    max = Math.max(max, h * w);
                }
                s.push(j);
            }
        }
        return max;
    }

    public int maximalRectangleB(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(largestRectangleArea(height), maxArea);
        }
        return maxArea;
    }
    public int largestRectangleArea(int[] height) {
        if(height==null || height.length==0) {
            return 0;
        }
        int maxArea = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0;i<height.length;i++) {
            while(!stack.isEmpty() && height[i]<=height[stack.peek()]) {
                int index = stack.pop();
                int curArea = stack.isEmpty()?i*height[index]:(i-stack.peek()-1)*height[index];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int index = stack.pop();
            int curArea = stack.isEmpty()?height.length*height[index]:(height.length-stack.peek()-1)*height[index];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }

    /**creek*/
    public int maximalRectangleC(char[][] matrix) {
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n + 1];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    height[i][j] = 0;
                } else {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int area = maxAreaInHist(height[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
    private int maxAreaInHist(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int max = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, height[t]
                        * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }
}
