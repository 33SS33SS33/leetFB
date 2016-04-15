package rectangle;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 * Tags: Array
 */
class RotateImg {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2 }, { 4, 3 } };
        new RotateImg().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        new RotateImg().rotate2(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * navie
     */
    public void rotateA(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int m = matrix.length;
        int[][] result = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                result[j][m - 1 - i] = matrix[i][j];
            }
        }
        matrix = result;
    }

    public void rotateB(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int m = matrix.length;
        int[][] result = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                result[j][m - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }

    /**
     * Get the length of matrix
     * Do level by level, each level edge by edge
     * In-place solutions overwrites original matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int tmp = matrix[i][j]; // save in tmp var
                matrix[i][j] = matrix[n - j - 1][i]; // first col
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]; // last row
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]; // last col
                matrix[j][n - i - 1] = tmp;
            }
        }
    }

    /**
     * In-place Solution~ using the relation "matrix[i][j] = matrix[n-1-j][i]",
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
