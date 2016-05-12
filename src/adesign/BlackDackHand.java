package adesign;

/**
 * Created by GAOSHANSHAN835 on 2016/5/12.
 */

import java.util.ArrayList;

/**
 * In the above code, we have implemented Deck with generics but restricted the type
 * of T to Card. We have also implemented Card as an abstract class, since methods like
 * value() don't make much sense without a specific game attached to them. (You could
 * make a compelling argument that they should be implemented anyway, by defaulting
 * to standard poker rules.)
 * Now, let's say we're building a blackjack game, so we need to know the value of the
 * cards. Face cards are 10 and an ace is 11 (most of the time, but that's the job of the Hand
 * class, not the following class).
 */
public class BlackDackHand extends Hand<BlackJackCard> {
    /* There are multiple possible scores for a blackjack hand,
    * since aces have multiple values. Return the highest possible
    * score that's under 21, or the lowest score that's over. */
    public int score() {
        ArrayList<Integer> scores = possibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            } else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    /* return a list of all possible scores this hand could have
    * (evaluating each ace as both 1 and 11 */
    private ArrayList<Integer> possibleScores() {
        return new ArrayList<Integer>();
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean isBlackJack() {
        return true;
    }
}