package top50Google;

import java.util.stream.IntStream;

public class RemoveAllOnesWithRowandColumnFlips {
    /**
     * You are given an m x n binary matrix grid.
     * In one operation, you can choose any row or column and flip each value in that row or column
     * (i.e., changing all 0's to 1's, and all 1's to 0's).
     * Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.
     */
    public boolean removeOnes(int[][] g) {
        int m = g.length, n = g[0].length;
        IntStream.range(0, n).forEach(j -> {
            if (g[0][j] == 1) flipColumn(j, g);
        });

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (g[i][j] != g[i][j - 1]) return false;
            }
        }
        return true;
    }

    private void flipColumn(int j, int[][] g) {
        IntStream.range(0, g.length).forEach(i -> {
            g[i][j] = 1 - g[i][j];
        });
    }
}
