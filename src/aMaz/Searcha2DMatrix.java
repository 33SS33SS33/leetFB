package aMaz;

/**
 * Tags: Array, Binary Search
 */
class Searcha2DMatrix {
    public static void main(String[] args) {
        Searcha2DMatrix s = new Searcha2DMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(s.searcha2DMatrix(matrix, 1));
        System.out.println(s.searcha2DMatrix(matrix, 30));
        System.out.println(s.searcha2DMatrix(matrix, 100));
//        System.out.println(s.searcha2DMatrixb(matrix, 0));
    }

    /**
     * * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * Given target = 3, return true.
     * 先用行进行二分搜索 确定行之后对列进行二分搜索
     * Binary search to locate row, then binary search in a row
     * O(logm + logn)  空间上只需两个辅助变量，因而是O(1)
     * http://blog.csdn.net/linhuanmars/article/details/24216235
     */
    public boolean searcha2DMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int l = 0;
        int r = matrix.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int row = r; //先确定行再确定列
        if (row < 0)
            return false;
        l = 0;
        r = matrix[0].length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[row][mid] == target)
                return true;
            if (matrix[row][mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * n * m matrix zigZagConversion to an array => matrix[x][y] => a[x * m + y]
     * an array zigZagConversion to n * m matrix => a[x] =>matrix[x / m][x % m];
     * disadvantage: 1. m * n may overflow 2. / and % are expensive
     */
/*    public boolean searcha2DMatrixb(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midX = mid / n;
            int midY = mid % n;
            if (matrix[midX][midY] == target)
                return true;
            if (matrix[midX][midY] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }*/

}
