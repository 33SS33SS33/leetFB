package hard;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells. HARD TODO
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 * Tags: Backtracking, Hash Table
 */
class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        SudokuSolver s = new SudokuSolver();
        s.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {//trial. Try 1 through 9 for each cell
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; //Put c for this cell
                            if (solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int i, int j, char c) {
        for (int row = 0; row < 9; row++)//Check column
            if (board[row][j] == c)
                return false;
        for (int col = 0; col < 9; col++) //Check row
            if (board[i][col] == c)
                return false;
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)//Check 3 x 3 block
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if (board[row][col] == c)
                    return false;
        return true;
    }

}