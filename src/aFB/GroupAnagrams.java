package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/4/15.
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * For the return value, each inner list's elements must follow the lexicographic order.
 * All inputs will be in lower-case.
 * The signature of the function had been updated to return list<list<string>> instead of list<string>,
 * as suggested here. If you still see your function signature return a list<string>, please click the reload button  to reset your code definition.
 * 使用哈希表 key是每个字符串排序过的 所以anagrams会有相同的key value就是字符串本身的值
 * 然后遍历字符串数组  把他们都装进字典 这样每个字典key下面就是一个anagrams group
 * 再把每个group排序后返回即可
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = { "dog", "dot", "cog", "log", "god", "tod" };
        System.out.println(groupAnagrams(strs).toString());
    }

    /**
     * 最好的
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);  //这句不懂
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }

}
