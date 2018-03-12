package test;

public class Piece {
    private PieceColor pieceColor;

    public Piece(PieceColor c) {
        pieceColor = c;
    }

    public void flip() {
        if (pieceColor == PieceColor.Black) {
            pieceColor = PieceColor.White;
        } else
            pieceColor = PieceColor.Black;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}