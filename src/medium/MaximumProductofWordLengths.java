package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */
/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 */

/**"首先把每一个word都转化成位的表示  就是一共26位 这个word里的字母对应的位置就变成1
 然后把这些word分别做& 如果结果为0就计算一下他们的乘积
 reduce里第三个参数可以设置一个初始值  这个值其实就是插入到了你这个数组的最前面  方便计算 重要"	*/
public class MaximumProductofWordLengths {
}
