package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String[]> res = new NQueens().solveNQueensa(4);
        for (String[] l : res) {
            for (String i : l) {
                System.out.println(i);
            }
        }
        System.out.println(new NQueens().solveNQueensaa(4));
    }

    //最好的
    public static List<String[]> solveNQueensa(int n) {
        List<String[]> res = new ArrayList<>();
        helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
        return res;
    }

    private static void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2,
                               String[] board, List<String[]> res) {
        if (r == board.length)
            res.add(board.clone());
        else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = d1[id1] = d2[id2] = true;
                    helper(r + 1, cols, d1, d2, board, res);
                    cols[c] = d1[id1] = d2[id2] = false;
                }
            }
        }
    }

    public static List<List<String>> solveNQueensaa(int n) {
        List<List<String>> solutions = new ArrayList<>();
        if (n <= 0) return solutions;
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n - 1]; // diagonal 1, from upper left to lower right. d1idx = cidx - ridx + n - 1
        boolean[] d2 = new boolean[2 * n - 1]; // diagonal 2, from upper right to lower left  d2idx = (n-cidx) - ridx + n - 2
        solveNQueens(n, 0, cols, d1, d2, new ArrayList<String>(), solutions);
        return solutions;
    }

    private static void solveNQueens(int n, int rowidx, boolean[] cols, boolean[] d1, boolean[] d2,
                                     List<String> oneSolution, List<List<String>> solutions) {
        if (rowidx == n) {
            solutions.add(new ArrayList<String>(oneSolution));
            return;
        }
        // in each recursive level, place one Queue in one row.
        char[] row = new char[n];
        Arrays.fill(row, '.');
        for (int colidx = 0; colidx < n; colidx++) {
            int d1idx = colidx - rowidx + n - 1;
            int d2idx = (n - colidx) - rowidx + n - 2;
            if (!cols[colidx] && !d1[d1idx] && !d2[d2idx]) {
                row[colidx] = 'Q';
                oneSolution.add(new String(row));
                cols[colidx] = d1[d1idx] = d2[d2idx] = true;

                solveNQueens(n, rowidx + 1, cols, d1, d2, oneSolution, solutions);

                row[colidx] = '.';
                oneSolution.remove(oneSolution.size() - 1);
                cols[colidx] = d1[d1idx] = d2[d2idx] = false;
            }
        }
    }

}