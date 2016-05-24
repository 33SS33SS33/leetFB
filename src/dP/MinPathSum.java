package dP;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * Tags: Array, DP
 */
class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        printMatrix(grid);
        System.out.println(minPathSum(grid));
        System.out.println(minPathSumB(grid));
        System.out.println(minPathSumC(grid));
    }

    /**
     * DP. bottom-up 优化版
     * row by row, use an array to store values
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] rowSum = new int[n];
        rowSum[0] = grid[0][0];
        for (int col = 1; col < n; col++)
            rowSum[col] = rowSum[col - 1] + grid[0][col];
        for (int row = 1; row < m; row++) {
            rowSum[0] += grid[row][0];
            for (int col = 1; col < n; col++) {
                rowSum[col] = Math.min(rowSum[col - 1], rowSum[col]) + grid[row][col];
            }
        }
        return rowSum[n - 1];
    }

    private static void printMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    /**
     * DP----creek
     */
    public static int minPathSumB(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // initialize top row
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // initialize left column
        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }
        // fill up the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int minPathSumC(int[][] grid) {
        return dfs(0, 0, grid);
    }

    public static int dfs(int i, int j, int[][] grid) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (i < grid.length - 1 && j < grid[0].length - 1) {
            int r1 = grid[i][j] + dfs(i + 1, j, grid);
            int r2 = grid[i][j] + dfs(i, j + 1, grid);
            return Math.min(r1, r2);
        }
        if (i < grid.length - 1) {
            return grid[i][j] + dfs(i + 1, j, grid);
        }
        if (j < grid[0].length - 1) {
            return grid[i][j] + dfs(i, j + 1, grid);
        }
        return 0;
    }
}