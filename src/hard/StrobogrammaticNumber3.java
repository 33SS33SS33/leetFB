package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */
/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * word1 and word2 may be the same and they represent two individual words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * Note:
 * You may assume word1 and word2 are both in the list.
 */
/**主要思路是比如求50-100范围的时候 先计算小于50的个数  再计算小于100的个数 然后用小于100的个数减去小于50的个数
 然后单独计算小于i的个数的时候 是直接用数学方法算出来  i的位数-1 的个数  然后再算出来i的位数一共有多少个 然后从这些里面找出来小于i的 然后把两个个数加起来*/
public class StrobogrammaticNumber3 {
}