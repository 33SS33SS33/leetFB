package test;

public class Player {
    private PieceColor pieceColor;

    public Player(PieceColor c) {
        pieceColor = c;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public int getScore() {
        return OthelloGame.getInstance().getBoard().getScoreForColor(pieceColor);
    }

    public boolean playPiece(int row, int col) {
        return OthelloGame.getInstance().getBoard().placePiece(row, col, pieceColor);
    }
}
