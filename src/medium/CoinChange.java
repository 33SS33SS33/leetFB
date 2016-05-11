package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */
/**
 * "You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * Note:
 * You may assume that you have an infinite number of each kind of coin."
 */

/**
 * "使用dp解
 * 通项公式为
 * res[i] = min([res[i-j] for j in coins if i-j>=0]) + 1
 * 就是要从0到amount 挨个算  当前的这个数量一定是之前的某一个数量然后加上了一个coin里的数量得来的 然后就是取最小的即可
 *
 * 注意一下初始值的设置  设置的是一个不可能得数从而才能知道什么时候返回-1"
 */
public class CoinChange {
}
