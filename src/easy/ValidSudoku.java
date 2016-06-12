package easy;

import java.util.*;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled
 * with the character '.'.
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only
 * the filled cells need to be validated.
 * Tags: Hashtable
 */
class ValidSudoku {
    public static void main(String[] args) {
        ValidSudoku v = new ValidSudoku();
        char[][] board = new char[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = '.';
                } else if (i == 0 && j != 0) {
                    board[i][j] = (char) ('0' + j + 1);
                } else {
                    board[i][j] = '.';
                }
            }
        }
        v.printBoard(board);
        System.out.println(v.isValidSudoku(board));
        System.out.println(v.isValidSudokuB(board));
        System.out.println(v.isValidSudokuC(board));
    }

    /**Each time send the coordinates to check if the board is partially valid.*/
    public boolean isValidSudokuA(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isParticallyValid(board, i, 0, i, 8))
                return false;
            if (!isParticallyValid(board, 0, i, 8, i))
                return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isParticallyValid(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2))
                    return false;
            }
        }
        return true;
    }

    private boolean isParticallyValid(char[][] board, int x1, int y1, int x2, int y2) {
        Set singleSet = new HashSet();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (board[i][j] != '.')
                    if (!singleSet.add(board[i][j]))
                        return false;
            }
        }
        return true;
    }
    /**
     * Use three arrays of integers to do masking
     */
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[] sqr = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if ((row[i] & 1 << num) > 0)
                        return false; // already in row
                    else
                        row[i] |= 1 << num;
                    if ((col[j] & 1 << num) > 0)
                        return false;// already in col
                    else
                        col[j] |= 1 << num;
                    int sqrIdx = (i - i % 3) + j / 3; // note the square idx
                    if ((sqr[sqrIdx] & 1 << num) > 0)
                        return false; // already
                    else
                        sqr[sqrIdx] |= 1 << num;
                }
            }
        }
        return true;
    }

    /**
     * hashtable, index as key, mask as value
     */
    public boolean isValidSudokuB(char[][] board) {
        Map<Integer, Integer> row = new HashMap<Integer, Integer>();
        Map<Integer, Integer> col = new HashMap<Integer, Integer>();
        Map<Integer, Integer> sqr = new HashMap<Integer, Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int rowMask = row.containsKey(i) ? row.get(i) : 0;
                    if ((rowMask & 1 << num) > 0) {
                        return false;
                    } else {
                        row.put(i, rowMask | 1 << num);
                    }
                    int colMask = col.containsKey(j) ? col.get(j) : 0;
                    if ((colMask & 1 << num) > 0) {
                        return false;
                    } else {
                        col.put(j, colMask | 1 << num);
                    }
                    int sqrIdx = (i - i % 3) + j / 3;
                    int sqrMask = sqr.containsKey(sqrIdx) ? sqr.get(sqrIdx) : 0;
                    if ((sqrMask & 1 << num) > 0) {
                        return false;
                    } else {
                        sqr.put(sqrIdx, sqrMask | 1 << num);
                    }
                }
            }
        }
        return true;
    }

    /**
     * creek--------
     * 对于每一行，每一列，每个九宫格进行验证，总共需要27次验证，每次看九个元素。
     * 所以时间复杂度就是O(3*n^2), n=9
     */
    public boolean isValidSudokuC(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        // check each column
        for (int i = 0; i < 9; i++) {
            boolean[] m = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (m[(int) (board[i][j] - '1')]) {
                        return false;
                    }
                    m[(int) (board[i][j] - '1')] = true;
                }
            }
        }
        //check each row
        for (int j = 0; j < 9; j++) {
            boolean[] m = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    if (m[(int) (board[i][j] - '1')]) {
                        return false;
                    }
                    m[(int) (board[i][j] - '1')] = true;
                }
            }
        }
        //check each 3*3 matrix
        for (int block = 0; block < 9; block++) {
            boolean[] m = new boolean[9];
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (m[(int) (board[i][j] - '1')]) {
                            return false;
                        }
                        m[(int) (board[i][j] - '1')] = true;
                    }
                }
            }
        }
        return true;
    }

    private void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
