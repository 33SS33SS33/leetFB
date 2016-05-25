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
        List<Integer> res4 = spiralOrderC(matrix);
        System.out.print(res.toString());
        System.out.print(res3.toString());
        System.out.print(res4.toString());
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

    /**
     * creek----------
     */
    public static ArrayList<Integer> spiralOrderC(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return result;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        while (m > 0 && n > 0) {
            //if one row/column left, no circle can be formed
            if (m == 1) {
                for (int i = 0; i < n; i++) {
                    result.add(matrix[x][y++]);
                }
                break;
            } else if (n == 1) {
                for (int i = 0; i < m; i++) {
                    result.add(matrix[x++][y]);
                }
                break;
            }
            //below, process a circle
            //top - move right
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y++]);
            }
            //right - move down
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x++][y]);
            }
            //bottom - move left
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y--]);
            }
            //left - move up
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x--][y]);
            }
            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }
        return result;
    }

}
