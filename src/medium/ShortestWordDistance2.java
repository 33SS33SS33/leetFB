package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the
 * list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
 * Design a class which receives a list of words in the constructor, and implements a method that
 * takes two words word1 and word2 and return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * <p/>
 * 先用哈希表记录单词出现的每一个位置
 * 然后找出来这两个单词的位置 遍历一下 找出来最小的距离
 * 找距离的代码比较重要
 * while i < len(l1) and j < len(l2):
 * res = min(res, abs(l1[i]-l2[j]))
 * if l1[i] < l2[j]:
 * i += 1
 * else:
 * j += 1
 */
public class ShortestWordDistance2 {
    public static void main(String[] args) {
        String[] words = { "dog", "cat" };
        String word1 = "cog";
        String word2 = "car";
        int res = new ShortestWordDistance().shortestDistance(words, word1, word2);
        int res2 = new ShortestWordDistance().shortestDistanceb(words, word1, word2);
        System.out.println(res);
        System.out.println(res2);
    }
    /**
     * 最好的
     */
    private Map<String, List<Integer>> map;

    public void WordDistance(String[] words) {
        map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (map.containsKey(w)) {
                map.get(w).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(w, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int ret = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            int index1 = list1.get(i), index2 = list2.get(j);
            if (index1 < index2) {
                ret = Math.min(ret, index2 - index1);
                i++;
            } else {
                ret = Math.min(ret, index1 - index2);
                j++;
            }
        }
        return ret;
    }

}
