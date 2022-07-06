package top50Google;

//https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344908/C%2B%2BJavaPython-3-DP-Explanation-with-pictures-O(MN)-Time-O(N)-Space
public class MaximumNumberofPointswithCost {
    /**
     * You are given an m x n integer matrix points (0-indexed). Starting with 0 points,
     * you want to maximize the number of points you can get from the matrix.
     * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
     * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
     * For every two adjacent rows r and r + 1 (where 0 <= r < m - 1),
     * picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
     */
    public long maxPoints(int[][] P) {
        int m = P.length, n = P[0].length;
        long[] pre = new long[n];
        for (int i = 0; i < n; ++i) pre[i] = P[0][i];
        for (int i = 0; i < m - 1; ++i) {
            long[] lft = new long[n], rgt = new long[n], cur = new long[n];
            lft[0] = pre[0];
            rgt[n - 1] = pre[n - 1];
            for (int j = 1; j < n; ++j)
                lft[j] = Math.max(lft[j - 1] - 1, pre[j]);
            for (int j = n - 2; j >= 0; --j)
                rgt[j] = Math.max(rgt[j + 1] - 1, pre[j]);
            for (int j = 0; j < n; ++j)
                cur[j] = P[i + 1][j] + Math.max(lft[j], rgt[j]);
            pre = cur;
        }
        long ans = 0;
        for (int i = 0; i < n; ++i)
            ans = Math.max(ans, pre[i]);
        return ans;
    }
}
