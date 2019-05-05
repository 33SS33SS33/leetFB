package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by shanshan on 16/5/9.
 * "Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 * You may assume both pattern and str contains only lowercase letters."
 * * "用个dict来记录每个pattern的字母对应的word 然后dfs
 * 注意一下在什么时候删除key
 * 有点慢 待优化"
 */
public class WordPatternII {
    public static void main(String[] args) {
        System.out.println(new WordPatternII().wordPatternMatch("abab", "redblueredblue"));
        System.out.println(new WordPatternII().wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(new WordPatternII().wordPatternMatch("aabb", "xyzabcxzyabc"));
    }

    private Map<Character, String> map = new HashMap<>();
    private Set<String> set = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.isEmpty())
            return str.isEmpty();
        if (map.containsKey(pattern.charAt(0))) {
            String value = map.get(pattern.charAt(0));
            if (str.length() < value.length() || !str.substring(0, value.length()).equals(value))
                return false;
            if (wordPatternMatch(pattern.substring(1), str.substring(value.length())))
                return true;
        } else {
            for (int i = 1; i <= str.length(); i++) {
                if (set.contains(str.substring(0, i)))
                    continue;
                map.put(pattern.charAt(0), str.substring(0, i));
                set.add(str.substring(0, i));
                if (wordPatternMatch(pattern.substring(1), str.substring(i)))
                    return true;
                set.remove(str.substring(0, i));
                map.remove(pattern.charAt(0));
            }
        }
        return false;
    }

}
