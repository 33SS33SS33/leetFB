package medium;


/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 * Example:
 * Given n = 3.
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 * So you should return 1, because there is only one bulb is on.
 * "对于第i栈灯泡，当i的因子个数为奇数时，最终会保持点亮状态，例如9的因子为1，3，9
 * 而当i的因子个数为偶数时，最终会保持熄灭状态，例如8的因子为：1，2，4，8
 * 当且仅当i为完全平方数时，其因子个数为奇数
 * 为什么只有完全平方数的因子个数为奇数呢？
 * 因为除了完全平方数，其余数字的因子都是成对出现的，而完全平方数的平方根只会统计一次。"
 */

public class BulbSwitcher {

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

}
