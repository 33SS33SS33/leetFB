package aMaz;

/**
 * Created by shanshan on 2/12/19.
 */
public class RobotReturntoOrigin {
    /**
     * Input: "UD" Output: true
     * Explanation: The robot moves up once, and then down once.
     * All moves have the same magnitude,
     * so it ended up at the origin where it started.
     * Therefore, we return true.
     * Input: "LL" Output: false
     * Explanation: The robot moves left twice.
     * It ends up two "moves" to the left of the origin.
     * We return false because it is not at the origin at the end of its moves.
     */
    public boolean robotReturntoOrigin(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'R') x++;
            else if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }
}
