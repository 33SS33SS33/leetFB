package top50Google;

public class MinimumCosttoSetCookingTime {
    /**
     * A generic microwave supports cooking times for:
     * at least 1 second.
     * at most 99 minutes and 99 seconds.
     * To set the cooking time, you push at most four digits. The microwave normalizes what you push as four digits by prepending zeroes. It interprets the first two digits as the minutes and the last two digits as the seconds. It then adds them up as the cooking time. For example,
     * You push 9 5 4 (three digits). It is normalized as 0954 and interpreted as 9 minutes and 54 seconds.
     * You push 0 0 0 8 (four digits). It is interpreted as 0 minutes and 8 seconds.
     * You push 8 0 9 0. It is interpreted as 80 minutes and 90 seconds.
     * You push 8 1 3 0. It is interpreted as 81 minutes and 30 seconds.
     * You are given integers startAt, moveCost, pushCost, and targetSeconds. Initially, your finger is on the digit startAt. Moving the finger above any specific digit costs moveCost units of fatigue. Pushing the digit below the finger once costs pushCost units of fatigue.
     * There can be multiple ways to set the microwave to cook for targetSeconds seconds but you are interested in the way with the minimum cost.
     * Return the minimum cost to set targetSeconds seconds of cooking time.
     * Remember that one minute consists of 60 seconds.
     */
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int mins = targetSeconds / 60, secs = targetSeconds % 60;
        return Math.min(cost(mins, secs, startAt, moveCost, pushCost),
                cost(mins - 1, secs + 60, startAt, moveCost, pushCost));
    }

    private int cost(int mins, int secs, int startAt, int moveCost, int pushCost) {
        if (mins > 99 || secs > 99 || mins < 0 || secs < 0) return Integer.MAX_VALUE;
        String s = Integer.toString(mins * 100 + secs);
        char curr = (char) (startAt + '0');
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == curr) res += pushCost;
            else {
                res += pushCost + moveCost;
                curr = s.charAt(i);
            }
        }
        return res;
    }
}
