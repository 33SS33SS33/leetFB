package TopInterview;


class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(10, 20));
        System.out.println(uniquePathsDP(10, 20));
    }

    /**
     * DP, top-down approach
     * use a matrix to store # of paths
     * base cases are, when m <= 0 or n <= 0, no way
     * when m == 1 or n == 1, only 1 way (straight down or straight right)
     */
    static int[][] paths = new int[101][101];

    public static int uniquePathsDP(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;
        if (paths[m][n] == 0)
            paths[m][n] = uniquePathsDP(m - 1, n) + uniquePathsDP(m, n - 1);
        return paths[m][n];
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot
     * is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there? Note: m and n will be at most 100.
     * Tags: Array, DP
     * 用DP 动态规划 状态转移方程式 F(m,n) = F(m-1,n) + F(m, n-1) 这样一直到起点 用迭代的办法即可
     */
    //complexity O(n*m)
    public static int uniquePaths(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            map[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            map[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1];
            }
        }
        return map[m - 1][n - 1];
    }


}