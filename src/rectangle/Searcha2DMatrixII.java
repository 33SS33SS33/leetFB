package rectangle;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * Consider the following matrix:
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 * <p/>
 * 首先row设置从第一行开始 列从最后一行开始  先移动列
 * 直到当前比target小  那么就往下移动行 如果碰见了元素超过了 target 就继续向左移动列
 */
public class Searcha2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } };
        Searcha2DMatrixII s = new Searcha2DMatrixII();
        System.out.println(s.searchMatrixA(matrix, 5));
        System.out.println(s.searchMatrixB(matrix, 5));
    }

    /**
     * 最好的
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

    public boolean searchMatrixB(int[][] matrix, int target) {
        return searchMatrix(matrix, 0, 0, matrix.length, matrix[0].length, target);
    }

    boolean searchMatrix(int[][] matrix, int stX, int stY, int edX, int edY, int target) {
        if (stX >= edX || stY >= edY)
            return false;
        int max = matrix[edX - 1][edY - 1];
        int min = matrix[stX][stY];
        // min <= target <= max
        if (min <= target && target <= max) {
            int mdX = (stX + edX) / 2;
            int mdY = (stY + edY) / 2;
            if (matrix[mdX][mdY] == target) {
                return true;
            }
            return searchMatrix(matrix, stX, stY, mdX, mdY, target) ||
                    searchMatrix(matrix, stX, mdY, mdX, edY, target) ||
                    searchMatrix(matrix, mdX, stY, edX, mdY, target) ||
                    searchMatrix(matrix, mdX, mdY, edX, edY, target);
        }
        return false;
    }

}
