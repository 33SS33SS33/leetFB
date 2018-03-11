package test;

import test.Color;
import test.Game;

/**
 * Created by shanshan on 3/11/18.
 */
public class Player {
    private Color color;

    public Player(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return Game.getInstance().getBoard().getScoreForColor(color);
    }

    public boolean playPiece(int row, int col) {
        return Game.getInstance().getBoard().placePiece(row, col, color);
    }
}
