package dP;

/**
 * "You are playing the following Nim Game with your friend: There is a heap of stones on the table,
 * each time one of you take turns to remove 1 to 3 stones.
 * The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
 * Both of you are very clever and have optimal strategies for the game.
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 * For example
 * if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove,
 * the last stone will always be removed by your friend."
 */

/**
 * 用dp的思想想的时候会发现 若当前是n 那么n的胜负之和n-1 n-2 n-3胜负有关 如果这三个都必胜 那么n必败
 * 如其中有一个必败 那么这次就一定能必胜 所以是4的倍数就输
 **/
public class NimGame {
    public static void main(String[] args) {
        System.out.println(canWinNimB(4));
    }

/*    public boolean canWinNim(int n) {
        return (n & 0b11) != 0;
    }*/

    public static boolean canWinNimB(int n) {
        if (n % 4 == 0)
            return false;
        else
            return true;
    }
}
