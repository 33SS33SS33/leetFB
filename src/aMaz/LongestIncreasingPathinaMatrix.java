package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/5/23. HARD
 */
public class LongestIncreasingPathinaMatrix {
    public static void main(String[] args) {
        LongestIncreasingPathinaMatrix r = new LongestIncreasingPathinaMatrix();
        int[][] nums = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(r.longestIncreasingPath(nums));
    }

    /**
     * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
     * Do DFS from every cell
     * Compare every 4 direction and skip cells that are out of boundary or smaller
     * Get matrix max from every cell's max
     * Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
     * The key is to cache the distance because it's highly possible to revisit a cell
     * <p>
     * HARD, Given an integer matrix, find the length of the longest increasing path.
     * From each cell, you can either move to four directions: left, right, up or down.
     * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     * nums = [
     * [9,9,4],
     * [6,6,8],
     * [2,1,1]
     * ] Return 4 The longest increasing path is [1, 2, 6, 9].
     * nums = [
     * [3,4,5],
     * [3,2,6],
     * [2,2,1]
     * ] Return 4 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     * Depth-first Search Topological Sort Memoization
     */
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0)
            return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }

}
