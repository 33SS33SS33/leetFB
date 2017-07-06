package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by
 * 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * For example,
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
 * 从矩阵的四条边开始  如果碰到0的元素 就用BFS查找与他临近的元素 然后把这些元素统统置为Y
 * 一直到结束  然后把这些Y的变成O 剩下那些本来为O的就可以变成X
 * 因为和边框上的元素有连接  就说明这些元素没有围起来
 */
class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solveB(board);
        for (char[] i : board) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /**
     * creek 最好的
     */
    public void solveB(char[][] board) {
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

    /**
     * 所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，
     * 同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，
     * 但是事实上这些结点会有很多重复，假设从中点出发，
     * 可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）
     * Use a queue to store index to do BFS
     * A 2d boolean array to remember whether a point is visited
     * A 2d int array to represent 4-connectivity
     * Traverse through the board and BFS at where it's 'O' and not visited
     * Set surround as true at first
     * Create an integer list for points to change
     * Check points around to see whether there is an 'O' point within the board
     * and not visited
     * If so, add it to queue, set visited true
     * If not, it's not surrounded
     */
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        Queue<Integer> q = new LinkedList<Integer>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    boolean surround = true;
                    List<Integer> pointsToChange = new ArrayList<Integer>();
                    q.add(i * n + j); // add root
                    visited[i][j] = true; // set root visited
                    while (q.size() > 0) { // BFS
                        int point = q.poll(); // get from queue
                        pointsToChange.add(point);
                        int x = point / n; // get coordinates
                        int y = point % n;
                        // try 4 direction
                        for (int k = 0; k < dir.length; k++) {
                            int nextX = x + dir[k][0];
                            int nextY = y + dir[k][1];
                            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) { // within board
                                if (board[nextX][nextY] == 'O' && !visited[nextX][nextY]) // add to queue
                                    q.add(nextX * n + nextY);
                                visited[nextX][nextY] = true; // set visited
                            } else
                                surround = false; // false if on the boundry
                        }
                    }
                    if (surround)
                        for (int p : pointsToChange)
                            board[p / n][p % n] = 'X'; // set to 'X'
                }
            }
        }
    }


}
