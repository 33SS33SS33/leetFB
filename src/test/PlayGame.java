package test;

public class PlayGame {
    public static void main(String args[]) {
        OthelloGame othelloGame = OthelloGame.getInstance();
        othelloGame.getBoard().init();
        Automator automator = Automator.getInstance();
        automator.init(othelloGame.getPlayers());
        while (!automator.isOver() && automator.playRandom()) {
        }
        if (othelloGame.getPlayers()[0].getScore() > othelloGame.getPlayers()[1].getScore()) {
            System.out.print("player one wins!");
        } else if (othelloGame.getPlayers()[0].getScore() == othelloGame.getPlayers()[1].getScore()) {
            System.out.print("it is a draw");
        } else {
            System.out.print("player two wins!");
        }
    }
}
