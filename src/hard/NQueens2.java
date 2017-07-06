package hard;

/**
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 * Tags: Backtracking
 */
class NQueens2 {
    public static void main(String[] args) {
        System.out.println(new NQueens2().totalNQueensb(2));
        System.out.println(new NQueens2().totalNQueensb(5));
    }

    //最好的
    int result = 0;

    public int totalNQueensb(int n) {
        boolean[] column = new boolean[n];
        boolean[] d1 = new boolean[2 * n - 1];
        boolean[] d2 = new boolean[2 * n - 1];
        helper(0, n, column, d1, d2);
        return result;
    }

    private void helper(int row, int n, boolean[] column, boolean[] d1, boolean[] d2) {
        if (row == n) {
            result++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!column[col] && !d1[col + row] && !d2[n - 1 - row + col]) {
                column[col] = d1[col + row] = d2[n - 1 - row + col] = true;
                helper(row + 1, n, column, d1, d2);
                column[col] = d1[col + row] = d2[n - 1 - row + col] = false;
            }
        }
    }

}
