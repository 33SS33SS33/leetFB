package test;

public class OthelloGame {
    private Player[] players;
    private static OthelloGame instance;
    private Board board;
    private final int ROWS = 10;
    private final int COLS = 10;

    private OthelloGame() {
        board = new Board(ROWS, COLS);
        players = new Player[2];
        players[0] = new Player(PieceColor.Black);
        players[1] = new Player(PieceColor.White);

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
