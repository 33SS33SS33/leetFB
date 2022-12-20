package aMaz;

/**
 * Tags: Array
 */
class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 0}, {4, 3, 2}, {1, 0, 5}};
        SetMatrixZeroes a = new SetMatrixZeroes();
        a.setMatrixZeroes(matrix);
        for (int[] i : matrix) {
            for (int j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // http://blog.csdn.net/linhuanmars/article/details/24066199

    /**
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     * Go through the matrix and use first row and first col to remember which cols and rows are to be sets
     * Use two flags for whether first row and first col should be set
     * 这样的做法只需要两个额外变量，所以空间复杂度是O(1)。
     * 时间上来说上面三种方法都是一样的，需要进行两次扫描，一次确定行列置0情况，一次对矩阵进行实际的置0操作，所以总的时间复杂度是O(m*n)。
     */
    public static void setMatrixZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        //mark zeros on first row and column
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0)
                        firstRow = true;
                    if (j == 0)
                        firstCol = true;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //set first column and row
        if (firstRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
