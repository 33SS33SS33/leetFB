package aFB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/8.
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 这道题很重要
 * 使用了双向BFS 即先从起点向下搜索一层  然后再从终点向上搜索一层 然后直到两个相遇 这里的一层就是指所有那些和上一层的单词只差一个字母的单词
 * 答案的代码写的非常好
 * 使用了set得性质 还有列表生成式的使用
 * 记得把遍历过的单词剔除 要不会打环
 * 第二遍看了思路 重要
 */
public class WordLadder {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] arr = {"hot", "dot", "dog", "lot", "log"};
        HashSet<String> dict = new HashSet<String>(Arrays.asList(arr));
        System.out.println(new WordLadder().ladderLength(start, end, dict));
        System.out.println(new WordLadder().ladderLengthB(start, end, dict));
    }

    /**
     * creek 最好的
     */
    public int ladderLengthB(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));
        wordDict.add(endWord);
        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;
            if (word.equals(endWord)) {
                return top.numSteps;
            }
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if (wordDict.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.numSteps + 1));
                        wordDict.remove(newWord);
                    }
                    arr[i] = temp;
                }
            }
        }
        return 0;
    }

    /**
     * 我们先给题目进行图的映射，顶点则是每个字符串，然后两个字符串如果相差一个字符则我们进行连边。
     * 接下来看看这个方法的优势，注意到我们的字符集只有小写字母，而且字符串长度固定，假设是L。
     * 那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个字符串可能存在的边是25*L条。
     * 接下来就是检测这些边对应的字符串是否在字典里，就可以得到一个完整的图的结构了。根据题目的要求，
     * 等价于求这个图一个顶点到另一个顶点的最短路径，一般我们用广度优先搜索（不熟悉搜索的朋友可以看看Clone Graph）即可。
     * 这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，而长度为L的字符串总共有26^L，
     * 所以时间复杂度是O(min(26^L, size(dict))，
     * 空间上需要存储访问情况，也是O(min(26^L, size(dict))
     */
    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (start == null || end == null || start.length() == 0 || end.length() == 0
                || start.length() != end.length())
            return 0;
        LinkedList<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        int level = 1;
        int lastNum = 1;
        int curNum = 0;
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            lastNum--;
            for (int i = 0; i < cur.length(); i++) {
                char[] charCur = cur.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    charCur[i] = c;
                    String temp = new String(charCur);
                    if (temp.equals(end))
                        return level + 1;
                    if (dict.contains(temp) && !visited.contains(temp)) {
                        curNum++;
                        queue.offer(temp);
                        visited.add(temp);
                    }
                }
            }
            if (lastNum == 0) {
                lastNum = curNum;
                curNum = 0;
                level++;
            }
        }
        return 0;
    }


    class WordNode {
        String word;
        int numSteps;

        public WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }
}
