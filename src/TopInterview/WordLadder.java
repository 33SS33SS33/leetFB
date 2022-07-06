package TopInterview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/8.
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Input: beginWord = "hit", endWord = "cog",wordList = ["hot","dot","dog","lot","log","cog"] Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",return its length 5.
 * beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"] Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
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
        System.out.println(new WordLadder().wordLadder(start, end, dict));
        System.out.println(new WordLadder().wordLadderb(start, end, dict));
    }

    public int wordLadder(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        int len = 1;
        HashSet<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        if (endSet.contains(target)) {
                            return len + 1;
                        }
                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }
        return 0;
    }
    
    public int wordLadderb(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<>();
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

    class WordNode {
        String word;
        int numSteps;

        public WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }
}
