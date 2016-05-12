package adesign;

import java.util.ArrayList;

/**
 * Created by GAOSHANSHAN835 on 2016/5/12.
 */
public class Hand <T extends Card> {
    protected ArrayList<T> cards = new ArrayList<T>();

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}