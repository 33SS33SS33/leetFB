package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class MaxSquare {
    public static void main(String[] args) {
        MaxSquare s = new MaxSquare();
        char[][] matrix = { { '1', '1', '0', '1' }, { '1', '1', '0', '1' },
                { '1', '1', '1', '1' } };
        System.out.println(s.maximalSquareA(matrix));
        System.out.println(s.maximalSquareB(matrix));

    }

    public int maximalSquareA(char[][] matrix) {
        int max = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                if (matrix[x][y] == '1') {
                    max = Math.max(max, maximalSquare(matrix, x, y));
                }
            }
        }
        return max;
    }

    int maximalSquare(char[][] matrix, final int x, final int y) {
        int l = 1;
        done:
        while (true) {
            for (int i = 0; i <= l; i++) {
                if (x + l >= matrix.length || y + l >= matrix[0].length) {
                    break done;
                }
                if (matrix[x + l][y + i] != '1') {
                    break done;
                }
                if (matrix[x + i][y + l] != '1') {
                    break done;
                }
            }
            l++;
        }
        return l * l;
    }

    public int maximalSquareB(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] t = new int[m][n];
        //top row
        for (int i = 0; i < m; i++) {
            t[i][0] = Character.getNumericValue(matrix[i][0]);
        }
        //left column
        for (int j = 0; j < n; j++) {
            t[0][j] = Character.getNumericValue(matrix[0][j]);
        }
        //cells inside
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int min = Math.min(t[i - 1][j], t[i - 1][j - 1]);
                    min = Math.min(min, t[i][j - 1]);
                    t[i][j] = min + 1;
                } else {
                    t[i][j] = 0;
                }
            }
        }
        int max = 0;
        //get maximal length
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (t[i][j] > max) {
                    max = t[i][j];
                }
            }
        }
        return max * max;
    }

}
