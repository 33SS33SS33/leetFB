package adesign;

import java.util.ArrayList;

/**
 * Created by GAOSHANSHAN835 on 2016/5/12.
 */
public class Deck<T extends Card> {
    private ArrayList<T> cards; // all cards, dealt or not
    private int dealtlndex = 0; // marks first undealt card

    public void setDeckOfCards(ArrayList<T> deckOfCards) {
    }

    public void snuffle() {
    }

    public int remainingCards() {
        return cards.size() - dealtlndex;
    }

/*    public T[] dealHand(int number) {
    }

    public T dealCard() {
    }*/
}
