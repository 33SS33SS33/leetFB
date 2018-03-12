package test;

public class Board {
    private int blackPieceNum = 0;
    private int whitePieceNum = 0;
    private Piece[][] board;

    public Board(int rows, int cols) {
        board = new Piece[rows][cols];
    }

    //initialize center black and white pieces
    public void init() {
        int middleRow = board.length / 2;
        int middleCol = board[middleRow].length / 2;
        board[middleRow][middleCol] = new Piece(PieceColor.White);
        board[middleRow + 1][middleCol] = new Piece(PieceColor.Black);
        board[middleRow + 1][middleCol + 1] = new Piece(PieceColor.White);
        board[middleRow][middleCol + 1] = new Piece(PieceColor.Black);
        blackPieceNum = 2;
        whitePieceNum = 2;
    }

    public boolean placePiece(int row, int col, PieceColor pieceColor) {
        if (board[row][col] != null)
            return false;
        int[] results = new int[4];
        results[0] = flipSection(row - 1, col, pieceColor, Direction.up);
        results[1] = flipSection(row + 1, col, pieceColor, Direction.down);
        results[2] = flipSection(row, col - 1, pieceColor, Direction.left);
        results[3] = flipSection(row, col + 1, pieceColor, Direction.right);
        int flipped = 0;
        for (int result : results) {
            if (result > 0)
                flipped += result;
        }
        if (flipped < 0)
            return false;
        board[row][col] = new Piece(pieceColor);
        updateScores(pieceColor, flipped + 1);
        return true;
    }

    public int flipSection(int row, int col, PieceColor pieceColor, Direction d) {
        int r = 0, c = 0;
        switch (d) {
            case up:
                r = -1;
                break;
            case down:
                r = 1;
                break;
            case left:
                c = -1;
                break;
            case right:
                c = 1;
                break;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == null)
            return -1;
        if (board[row][col].getPieceColor() == pieceColor)
            return 0;
        int flipped = flipSection(row + r, col - c, pieceColor, d);
        if (flipped < 0)
            return -1;
        board[row][col].flip();
        return flipped + 1;
    }

    public void updateScores(PieceColor newPieceColor, int newPieces) {
        if (newPieceColor == PieceColor.White) {
            whitePieceNum += newPieces;
            blackPieceNum -= newPieces - 1;
        } else {
            whitePieceNum -= newPieces - 1;
            blackPieceNum += newPieces;
        }
    }

    public int getScoreForColor(PieceColor c) {
        if (c == PieceColor.Black)
            return blackPieceNum;
        else
            return whitePieceNum;
    }
}

