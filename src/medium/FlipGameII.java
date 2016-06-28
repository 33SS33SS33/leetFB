package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshan on 16/5/9.
 * "You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer
 * make a move and therefore the other person will be the winner.
 * Write a function to determine if the starting player can guarantee a win.
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity."
 * "加个memo会速度快一点  代码很简单
 * 最关键的判断还是要当前赢的时候只有当前这一次我可以翻但是我翻了之后下一次就翻不成
 * 其实不用判断轮到谁翻了"
 */

public class FlipGameII {
    public static void main(String[] args) {
        System.out.println(canWin("++++"));
        System.out.println(canWinb("++++"));
    }

    public static boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canWinb(String s) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
            // generate all possible sequence after every attempt
        }
            /*if(list.isEmpty())
            return false;*/
        for (String str : list) {
            if (!canWinb(
                    str)) // if there is any one way the next player can't win, take it and you'll win
                return true;
        }
        return false;
    }
}
