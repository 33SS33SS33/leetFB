package top50Google;

public class CheckifWordCanBePlacedInCrossword {
    /**
     * You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters
     * (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.
     * A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:
     * It does not occupy a cell containing the character '#'.
     * The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
     * There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
     * There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
     * Given a string word, return true if word can be placed in board, or false otherwise.
     */
    public boolean checkifWordCanBePlacedInCrossword(char[][] board, String word) {
        char[][] rotatedBoard = getRotated(board);
        String reversedWord = new StringBuilder(word).reverse().toString();
        return helper(board, word) || helper(rotatedBoard, word)
                || helper(board, reversedWord) || helper(rotatedBoard, reversedWord);
    }

    private boolean helper(char[][] board, String word) {
        for (char[] row : board) {
            for (String token : String.valueOf(row).split("#")) {
                if (word.length() == token.length() && canFit(word, token))
                    return true;
            }
        }
        return false;
    }

    private char[][] getRotated(char[][] board) {
        final int m = board.length;
        final int n = board[0].length;
        char[][] rotated = new char[n][m];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                rotated[j][i] = board[i][j];
        return rotated;
    }

    private boolean canFit(final String letters, final String token) {
        for (int i = 0; i < letters.length(); ++i)
            if (token.charAt(i) != ' ' && token.charAt(i) != letters.charAt(i))
                return false;
        return true;
    }
}
