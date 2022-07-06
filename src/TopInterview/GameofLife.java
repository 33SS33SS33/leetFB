package TopInterview;

/**
 * Created by shanshan on 16/5/9. medium
 * "According to the Wikipedia's article: "The OthelloGame of Life, also known simply as Life, is a cellular automaton
 * devised by the British mathematician John Horton Conway in 1970."
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
 * (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Input:
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * Output:
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * Write a function to compute the next state (after one update) of the board given its current state.
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite,
 * which would cause problems when the active area encroaches the border of the array. How would you address these problems?"
 * "注意题目说的是一次性更新 不能更新一个之后 然后再更新周围的
 * 所以使用位操作 大循环是遍历数组内的每一个元素  还有个小循环 就是访问当前元素周围的邻居
 * 然后有一个count变量来记录他周围的邻居（包括他自己）有几个1
 * 然后根据count的值进行判断
 * count值这里要注意 判断条件
 * 题目中说的下一代还能是1的有三种
 * 一种是 当前是死的  然后有三个活着的邻居 另一种是 当前是活的 然后有两个或者三个活着的邻居
 * 第一种的count是3
 * 第二种的情况 若是两个活着的邻居  count也是3（因为他自己是1） 剩下就是三个邻居活着 那么count减去他自己 是3
 * 这些所有的结果 都记录在当卡前元素的第二位（和1与的时候 1是01 所以第二位有没有无所谓）
 * 最后把数组的元素都往右移动一位即可"
 */
public class GameofLife {
    //https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
    int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0},
            {-1, 1}};

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for (int[] d : dir) {
                    if (d[0] + i < 0 || d[0] + i >= board.length || d[1] + j < 0
                            || d[1] + j >= board[0].length)
                        continue;
                    if (board[d[0] + i][d[1] + j] == 1 || board[d[0] + i][d[1] + j] == 2)
                        live++;
                }
                if (board[i][j] == 0 && live == 3)
                    board[i][j] = 3;
                if (board[i][j] == 1 && (live < 2 || live > 3))
                    board[i][j] = 2;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 2;
            }
        }
    }
}
