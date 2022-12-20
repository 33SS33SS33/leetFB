package aMaz;

import java.util.LinkedList;
import java.util.List;

class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.surroundedRegions(board);
        for (char[] i : board) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //http://blog.csdn.net/linhuanmars/article/details/22904855

    /**
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * Tags: BFS
     * 从矩阵的四条边开始, 如果碰到0的元素 就用BFS查找与他临近的元素 然后把这些元素统统置为Y
     * 一直到结束,然后把这些Y的变成O 剩下那些本来为O的就可以变成X 因为和边框上的元素有连接  就说明这些元素没有围起来
     */
    protected Integer ROWS = 0;
    protected Integer COLS = 0;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.ROWS = board.length;
        this.COLS = board[0].length;
        List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
        // Step 1). construct the list of border cells
        for (int r = 0; r < this.ROWS; ++r) {
            borders.add(new Pair(r, 0));
            borders.add(new Pair(r, this.COLS - 1));
        }
        for (int c = 0; c < this.COLS; ++c) {
            borders.add(new Pair(0, c));
            borders.add(new Pair(this.ROWS - 1, c));
        }
        // Step 2). mark the escaped cells
        for (Pair<Integer, Integer> pair : borders) {
            this.DFS(board, pair.first, pair.second);
        }
        // Step 3). flip the cells to their correct final states
        for (int r = 0; r < this.ROWS; ++r) {
            for (int c = 0; c < this.COLS; ++c) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == 'E')
                    board[r][c] = 'O';
            }
        }
    }

    protected void DFS(char[][] board, int row, int col) {
        if (board[row][col] != 'O')
            return;
        board[row][col] = 'E';
        if (col < this.COLS - 1)
            this.DFS(board, row, col + 1);
        if (row < this.ROWS - 1)
            this.DFS(board, row + 1, col);
        if (col > 0)
            this.DFS(board, row, col - 1);
        if (row > 0)
            this.DFS(board, row - 1, col);
    }

    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }


    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0)
            return;
        int m = board.length;
        int n = board[0].length;
        //merge O's on left & right boarder
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                merge(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                merge(board, i, n - 1);
            }
        }
        //merge O's on top & bottom boarder
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                merge(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                merge(board, m - 1, j);
            }
        }
        //process the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void merge(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if (board[i][j] != 'O')
            return;
        board[i][j] = '#';
        merge(board, i - 1, j);
        merge(board, i + 1, j);
        merge(board, i, j - 1);
        merge(board, i, j + 1);
    }

}
