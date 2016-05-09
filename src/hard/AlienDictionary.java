package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 */
/**There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary,
 *  where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 For example,
 Given the following words in dictionary,
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".
 Note:
 You may assume all letters are in lowercase.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 */

/**这道题分两步  第一步是通过字典里的单词建成有向图, 这道题的真正意思是 每一个word都是一个单词 单词是按照字母顺序表示的 所以找到顺序就是找到上下单词里的第一个不同的字母
 然后用拓扑排序即可
 这道题的代码十分精简 特别是zip 还有set的使用
 */
public class AlienDictionary {
}
