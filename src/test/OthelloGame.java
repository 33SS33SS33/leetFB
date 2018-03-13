package test;

public class OthelloGame {
    private Player[] players;
    private Board board;
    private final int ROWS = 8;
    private final int COLS = 8;
    private static OthelloGame instance;

    private OthelloGame() {
        board = new Board(ROWS, COLS);
        players = new Player[2];
        players[0] = new Player(PieceType.O);
        players[1] = new Player(PieceType.X);

    }

    public static OthelloGame getInstance() {
        if (instance == null) {
            instance = new OthelloGame();
        }
        return instance;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }
}
