package aMaz;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * Given board =
 * [
 * ["ABCE"],
 * ["SFCS"],
 * ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * Tags: Array, Backtracking
 */
class WordSearch {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";
        // String word = "ABCCED";
        // String word = "ABCB";
        System.out.println(wordSearcha(board, word));
        System.out.println(wordSearchb(board, word));
    }
    //best
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row)
            for (int col = 0; col < this.COLS; ++col)
                if (this.backtrack(row, col, word, 0))
                    return true;
        return false;
    }

    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if (index >= word.length())
            return true;
        /* Step 2). Check the boundaries. */
        if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
                || this.board[row][col] != word.charAt(index))
            return false;
        /* Step 3). explore the neighbors in DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
            if (ret)
                break;
        }
        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }


    /**
     * 最好的
     * Use boolean array to remember whether a word is used Traverse each position and do DFS
     * 总的时间复杂度最坏是O(m^2*n^2)
     * 这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，
     * 往上下左右深度搜索是否有相等于word的字符串。这里注意每次从一个元素出发时要重置访问标记
     * （也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。
     * 深度优先搜索的算法就不再重复解释了，不了解的朋友可以看看wiki - 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，
     * E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的（因为一个顶点有固定上下左右四条边）。
     * 加上我们对每个顶点都要做一次搜索， 所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况，所以是O(m*n).
     * http://blog.csdn.net/linhuanmars/article/details/24336987
     */
    public static boolean wordSearcha(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null)
            return false;
        if (word.length() == 0)
            return true;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == word.charAt(0)) { // match the first char
                    if (dfs(board, i, j, word, 0))
                        return true;
                }
        return false;
    }

    /**
     * Remember position in board
     * Remember position in matched word
     */
    public static boolean dfs(char[][] board, int i, int j, String word, int n) {
        if (word.length() == n)
            return true;
        // outside board or doesn't match
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(n))
            return false;
        board[i][j] = '#'; // mark
        // searchinRotatedSortedArrayb 4 connectivity
        boolean res = dfs(board, i - 1, j, word, n + 1) || dfs(board, i + 1, j, word, n + 1)
                || dfs(board, i, j - 1, word, n + 1) || dfs(board, i, j + 1, word, n + 1);
        board[i][j] = word.charAt(n);// reset mark
        return res;
    }

    //类似上面的方法 位运算
    public static boolean wordSearchb(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (wordSearchb(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private static boolean wordSearchb(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length)
            return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length)
            return false;
        if (board[y][x] != word[i])
            return false;
        board[y][x] ^= 256;
        boolean exist = wordSearchb(board, y, x + 1, word, i + 1)
                || wordSearchb(board, y, x - 1, word, i + 1)
                || wordSearchb(board, y + 1, x, word, i + 1)
                || wordSearchb(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
    }

}