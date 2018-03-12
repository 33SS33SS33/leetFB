package test;

import java.util.ArrayList;

public class Automator {
    private Player[] players;
    private Player lastPlayer = null;
    public ArrayList<Location> remainingMoves = new ArrayList<>();
    private static Automator instance;

    private Automator() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
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

    public boolean playRandom() {
        shuffle();
        lastPlayer = lastPlayer == players[0] ? players[1] : players[0];
        for (int i = 0; i < remainingMoves.size(); i++) {
            Location loc = remainingMoves.get(i);
            boolean success = lastPlayer.playPiece(loc.getRow(), loc.getCol());

            if (success) {
                remainingMoves.remove(new Location(loc.getRow(), loc.getCol()));
                return true;
            }
        }
        return false;
    }

    public boolean isOver() {
        if (remainingMoves.size() == 0) {
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