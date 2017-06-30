package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshan on 16/5/9.
 * "You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 * For example, given s = "++++", after one move, it may become one of the following states:
 * [
 * "--++",
 * "+--+",
 * "++--"
 * ]
 * If there is no valid move, return an empty list []."
 */

//注意一下要先检查 只有当连续两个+的时候再翻转
public class FlipGame {
    public static void main(String[] args) {
        System.out.println(generatePossibleNextMovesa("++++"));
        System.out.println(generatePossibleNextMoves("++++"));
    }

    /**
     * 最好的
     */
    public static List<String> generatePossibleNextMovesa(String s) {
        List list = new ArrayList();
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i + 2));
        return list;
    }

    public static List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                list.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return list;
    }

}
