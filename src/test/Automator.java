package test;

import java.util.ArrayList;

/**
 * Created by shanshan on 3/11/18.
 */
public class Automator {
    private Player[] players;
    private Player lastPlayer = null;
    public ArrayList<Location> remainingMoves = new ArrayList<>();
    private static Automator instance;

    private Automator() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Location loc = new Location(i, j);
                remainingMoves.add(loc);
            }
        }
    }

    public static Automator getInstance() {
        if (instance == null)
            instance = new Automator();
        return instance;
    }

    public void init(Player[] ps) {
        players = ps;
        lastPlayer = ps[1];
    }

    public void shuffle() {
        for (int i = 0; i < remainingMoves.size(); i++) {
            int t = randomIntInRange(i, remainingMoves.size() - 1);
            Location other = remainingMoves.get(t);
            Location current = remainingMoves.get(i);
            remainingMoves.set(t, current);
            remainingMoves.set(i, other);
        }
    }

    public void removeLocation(int r, int c) {
        for (int i = 0; i < remainingMoves.size(); i++) {
            Location loc = remainingMoves.get(i);
            if (loc.isSameAs(r, c)) {
                remainingMoves.remove(i);
            }
        }
    }

    public Location getLocation(int index) {
        return remainingMoves.get(index);
    }

    public boolean playRandom() {
        Board board = Game.getInstance().getBoard();
        shuffle();
        lastPlayer = lastPlayer == players[0] ? players[1] : players[0];
        String color = lastPlayer.getColor().toString();
        for (int i = 0; i < remainingMoves.size(); i++) {
            Location loc = remainingMoves.get(i);
            boolean success = lastPlayer.playPiece(loc.getRow(), loc.getCol());

            if (success) {
                return true;
            }
        }
        return false;
    }

    public boolean isOver() {
        if (players[0].getScore() == 0 || players[1].getScore() == 0) {
            return true;
        }
        return false;
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }
}