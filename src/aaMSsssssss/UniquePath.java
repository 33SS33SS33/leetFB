package aaMSsssssss;

import java.util.Arrays;

public class UniquePath {
    /**
     * Time complexity: O(N×M).
     * Space complexity:O(N×M).
     */
    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for (int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for (int col = 1; col < m; ++col) {
            for (int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

    public int uniquePathsb(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePathsb(m - 1, n) + uniquePathsb(m, n - 1);
    }
}
