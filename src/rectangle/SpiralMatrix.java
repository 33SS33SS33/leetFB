package rectangle;

import java.util.*;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * Tags: Array
 */
class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 7}, {4, 5, 6, 9}, {7, 8, 9, 1}};
        List<Integer> res3 = SpiralMatrixA(matrix);
        List<Integer> res4 = SpiralMatrixB(matrix);
        System.out.println(res3.toString());
        System.out.println(res4.toString());
    }

    /**
     * 最好理解的
     */
    public static List<Integer> SpiralMatrixA(int[][] matrix) {
        List<Integer> elements = new ArrayList<Integer>();
        if (matrix.length == 0)
            return elements;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = -1;
        while (true) {
            for (int i = 0; i < n; i++) {
                elements.add(matrix[row][++col]);
            }
            if (--m == 0)
                break;
            for (int i = 0; i < m; i++) {
                elements.add(matrix[++row][col]);
            }
            if (--n == 0)
                break;
            for (int i = 0; i < n; i++) {
                elements.add(matrix[row][--col]);
            }
            if (--m == 0)
                break;
            for (int i = 0; i < m; i++) {
                elements.add(matrix[--row][col]);
            }
            if (--n == 0)
                break;
        }
        return elements;
    }

    public static List<Integer> SpiralMatrixB(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return res;
        }
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;
            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j--) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin++;
        }
        return res;
    }

}
