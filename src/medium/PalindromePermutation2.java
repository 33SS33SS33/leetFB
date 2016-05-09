package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */
/**
 "Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 For example:
 Given s = ""aabb"", return [""abba"", ""baab""].
 Given s = ""abc"", return []."
 */

/**"对于回文 只要构造前一半就行
 对于奇数的那个字符 只留一个放在中间即可 然后建立一个数组 里面存的就是备选的前一半的字符 然后dfs构造 注意要跳过连续的字符
 构造完了之后直接把前一半颠倒了添加即可"*/
public class PalindromePermutation2 {
}
