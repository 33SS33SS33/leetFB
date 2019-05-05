package aMaz;

/**
 * 首先row设置从第一行开始 列从最后一行开始  先移动列
 * 直到当前比target小  那么就往下移动行 如果碰见了元素超过了 target 就继续向左移动列
 */
public class Searcha2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        Searcha2DMatrixII s = new Searcha2DMatrixII();
        System.out.println(s.searchMatrixA(matrix, 5));
    }

    /**
     * * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     * Given target = 20, return false.
     * Search Space Reduction
     * Because the rows and columns of the matrix are sorted (from left-to-right and top-to-bottom, respectively),
     * we can prune O(m) or O(n) elements when looking at any particular value.
     * Time complexity : O(n+m)
     * Space complexity : O(1)
     */
    public boolean searchMatrixA(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int row = 0;
        int col = matrix[row].length - 1;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

}
