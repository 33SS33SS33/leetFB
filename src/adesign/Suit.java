package adesign;

/**
 * Created by GAOSHANSHAN835 on 2016/5/12.
 */

/**
 * Design the data structures for a generic deck of cards. Explain how you would
 * subclass the data structures to implement blackjack.
 */
public enum Suit {
    Club(0), Diamond(1), Heart(2), Spade(3);
    private int value;

    private Suit(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

/*    public static Suit getSuitFromValue(int value) {

    }*/
}