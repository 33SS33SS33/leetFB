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
        int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        List<Integer> res3 = spiralOrderA(matrix);
        List<Integer> res = spiralOrderB(matrix);
        List<Integer> res4 = spiralOrder(matrix);
        System.out.print(res.toString());
        System.out.print(res3.toString());
        System.out.print(res4.toString());
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
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

    /**
     * 最好理解的
     */
    public static List<Integer> spiralOrderA(int[][] matrix) {
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

    /**
     * Remember which level it is right now
     * Do level by level till reach center
     */
    public static List<Integer> spiralOrderB(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int lv = 0;
        while (2 * lv < m && 2 * lv < n) { // note 2 * level
            for (int i = lv; i < n - lv; i++)
                res.add(matrix[lv][i]); // right
            for (int i = lv + 1; i < m - lv; i++)
                res.add(matrix[i][n - lv - 1]); // down
            if (2 * lv == m - 1 || 2 * lv == n - 1)
                break; // reach last row/col
            for (int i = n - lv - 2; i >= lv; i--)
                res.add(matrix[m - lv - 1][i]);
            for (int i = m - lv - 2; i >= lv + 1; i--)
                res.add(matrix[i][lv]);
            lv++;
        }
        return res;
    }

}
