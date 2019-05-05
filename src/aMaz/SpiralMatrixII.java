package aMaz;

/**
 * Tags: Array
 */
class SpiralMatrixII {
    public static void main(String[] args) {
        int[][] mat = spiralMatrixII(3);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Given an integer n, generate a square matrix filled with elements from 1 to
     * n^2 in spiral order.
     * Given n = 3,You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     * 每个元素只访问一次，时间复杂度是O(n^2)
     * use startR, endR, startC, endC to mark the range
     * update relative range whenever finish filling up a row or column
     */
    public static int[][] spiralMatrixII(int n) {
        if (n <= 0)
            return new int[0][0];
        int[][] ans = new int[n][n];
        int i = 1;
        int startR = 0;
        int startC = 0;
        int endR = n - 1;
        int endC = n - 1;
        while (startR <= endR && startC <= endC) {
            for (int j = startC; j <= endC; j++)
                ans[startR][j] = i++;
            startR++;
            for (int j = startR; j <= endR; j++)
                ans[j][endC] = i++;
            endC--;
            for (int j = endC; j >= startC; j--)
                ans[endR][j] = i++;
            endR--;
            for (int j = endR; j >= startR; j--)
                ans[j][startC] = i++;
            startC++;
        }
        return ans;
    }


}
