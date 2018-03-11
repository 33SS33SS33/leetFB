package test;

/**
 * Created by shanshan on 3/11/18.
 */
public class PlayGame {
    public static void main(String args[]) {
        Game game = Game.getInstance();
        game.getBoard().init();
        Automator automator = Automator.getInstance();
        while (!automator.isOver()) {
            automator.playRandom();
        }
    }
}
