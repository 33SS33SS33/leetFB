package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 * DP 动态规划 状态转移方程：
 * dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1
 * 上式中，dp[x][y]表示以坐标(x, y)为右下角元素的全1正方形矩阵的最大长度（宽度）
 * 如果当前点为0  则dp[x][y]就是0  肯定没有以0 为右下角元素的全1正方形
 * 右边是另一种解法 未实现
 */
public class MaximalSquare {
    public static void main(String[] args) {
        MaximalSquare s = new MaximalSquare();
        char[][] matrix = {{'0', '0', '1', '1'}, {'0', '0', '1', '1'},
                {'1', '1', '1', '1'}};
        System.out.println(s.maximalSquare(matrix));
    }

    /**
     * 最好的  不懂啊  动态规划
     * Top, Left, and Top Left decides the size of the square.
     * If all of them are same, then the size of square increases by 1.
     * If they're not same, they can increase by 1 to the minimal square.
     * If you take an example and work it out,
     * it'll be much easier to understand when it comes to dynamic programing. :)
     * b[i][j] represent the edge length of the largest square ENDING at position (i, j)
     */
    // if you mention in your explanation that b[i][j]
    // represent the edge length of the largest square ENDING at position (i, j), it would be much clearer.
    //https://discuss.leetcode.com/topic/20801/extremely-simple-java-solution
    public int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

}
