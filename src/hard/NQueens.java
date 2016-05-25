package hard;

import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p/>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Tags: Backtracking, Bit Manipulation
 */
class NQueens {
    public static void main(String[] args) {
        List<String[]> res = new NQueens().solveNQueensB(4);
        for (String[] l : res) {
            for (String i : l) {
                System.out.println(i);
            }
        }
        //        System.out.println(new NQueens().solveNQueensB(3));
    }

    int limit, total; // limit is all ones, total is # of rows
    String[]       strings; // for a solution
    List<String[]> res; // solutions
    StringBuilder  sb; // for a row
    List<Integer>  indices; // store solution

    public List<String[]> solveNQueens(int n) {
        res = new ArrayList<String[]>();
        if (n <= 0)
            return res;
        total = n;
        strings = new String[n];
        sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(".");
        indices = new ArrayList<Integer>();
        limit = (1 << n) - 1;
        dfs(0, 0, 0);
        return res;
    }

    /**
     * Save indices of each line in a list
     * Retrieve the indices of each line when there is a solution
     */
    public void dfs(int h, int r, int l) {
        if (h == limit) {
            for (int i = indices.size() - 1; i >= 0; i--) {
                int gap = h - indices.get(i); // last position
                h = indices.get(i);
                int n = 0;
                while (gap > 0) {
                    n++;
                    gap >>= 1;
                }
                StringBuilder ans = new StringBuilder(sb);
                ans.setCharAt(n - 1, 'Q'); // note n - 1
                strings[i] = ans.toString();
            }
            res.add(strings); // add to result
            strings = new String[total]; // reset strings
            return;
        }
        indices.add(h); // add then remove
        int pos = limit & (~(h | r | l)); // set unsaved pos to zero, note ~
        while (pos != 0) {
            int p = pos & (-pos); // rightmost 1
            pos -= p; // note how to place a queen
            dfs(h + p, (r + p) << 1, (l + p) >> 1);
        }
        indices.remove(indices.size() - 1); // remove added h
    }

    public ArrayList<String[]> solveNQueensB(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        helper(n, 0, new int[n], res);
        return res;
    }

    private void helper(int n, int row, int[] columnForRow, ArrayList<String[]> res) {
        if (row == n) {
            String[] item = new String[n];
            for (int i = 0; i < n; i++) {
                StringBuilder strRow = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (columnForRow[i] == j)
                        strRow.append('Q');
                    else
                        strRow.append('.');
                }
                item[i] = strRow.toString();
            }
            res.add(item);
            return;
        }
        for (int i = 0; i < n; i++) {
            columnForRow[row] = i;
            if (check(row, columnForRow)) {
                helper(n, row + 1, columnForRow, res);
            }
        }
    }

    private boolean check(int row, int[] columnForRow) {
        for (int i = 0; i < row; i++) {
            if (columnForRow[row] == columnForRow[i]
                    || Math.abs(columnForRow[row] - columnForRow[i]) == row - i)
                return false;
        }
        return true;
    }
}