package hard;

/**
 * Created by shanshan on 16/5/9.
 */

/**
 * "Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 * You may assume both pattern and str contains only lowercase letters."
 * <p/>
 * * "用个dict来记录每个pattern的字母对应的word 然后dfs
 * 注意一下在什么时候删除key
 * 有点慢 待优化"
 */
public class WordPatternII {
}
