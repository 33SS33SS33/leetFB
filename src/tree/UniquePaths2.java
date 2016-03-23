package tree;

/**
 * Follow up for "Unique Paths":
 * <p/>
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * <p/>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p/>
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <p/>
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * <p/>
 * Note: m and n will be at most 100.
 * <p/>
 * Tags: Array, DP
 */
class UniquePaths2 {
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[1][1] = 1;
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
        System.out.println(uniquePathsWithObstaclesB(obstacleGrid));
    }

    /**
     * DP, bottom-up approach
     * build from end point to start point
     * for the grid paths at the rth row and cth column
     * paths[r][c] = obstacleGrid[r][c] == 1 ? 0
     * : paths[r + 1][c] + paths[r][c + 1];
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null)
            return 0;
        int m = obstacleGrid.length;
        if (m == 0)
            return 0;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[m + 1][n + 1];

        paths[m - 1][n] = 1;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                paths[r][c] = obstacleGrid[r][c] == 1 ? 0 : paths[r + 1][c] + paths[r][c + 1];
            }
        }
        return paths[0][0];
    }

    /**
     * creek
     */
    public static int uniquePathsWithObstaclesB(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        //left column
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        //top row
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        //fill up cells inside
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }
        return dp[m - 1][n - 1];
    }
}