package aMaz;

/**
 * Created by shanshan on 2/13/19.
 * At a lemonade stand, each lemonade costs $5.
 * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer,
 * so that the net transaction is that the customer pays $5.
 * Note that you don't have any change in hand at first.
 * Return true if and only if you can provide every customer with correct change.
 * Input: [5,5,5,10,20]Output: true
 * Input: [5,5,10]Output: true
 * Input: [10,10]Output: false
 * Input: [5,5,10,10,20]Output: false
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i : bills) {
            if (i == 5) five++;
            else if (i == 10) {
                five--;
                ten++;
            } else if (ten > 0) {
                ten--;
                five--;
            } else five -= 3;
            if (five < 0) return false;
        }
        return true;
    }
}
