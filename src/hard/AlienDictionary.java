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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**这道题分两步  第一步是通过字典里的单词建成有向图, 这道题的真正意思是 每一个word都是一个单词 单词是按照字母顺序表示的 所以找到顺序就是找到上下单词里的第一个不同的字母
 然后用拓扑排序即可
 这道题的代码十分精简 特别是zip 还有set的使用
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
        System.out.println(new AlienDictionary().alienOrder(words));
    }

    public String alienOrder(String[] words) {
        Node[] node = new Node[26];
        boolean[] happen = new boolean[26];
        for (int i = 0; i < 26; i++) {
            node[i] = new Node();
        }
        //Build the Graph
        for (int i = 0; i < words.length; i++) {
            int startPoint = 0, endPoint = 0;
            for (int j = 0; j < words[i].length(); j++) {
                happen[charToInt(words[i].charAt(j))] = true;
            }
            if (i != words.length - 1) {
                for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                        startPoint = charToInt(words[i].charAt(j));
                        endPoint = charToInt(words[i + 1].charAt(j));
                        break;
                    }
                }
            }
            if (startPoint != endPoint) {
                node[startPoint].neighbour.add(endPoint);
                node[endPoint].degree++;
            }
        }
        //Topological Sort
        Queue<Integer> queue = new LinkedList<Integer>();
        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (node[i].degree == 0 && happen[i]) {
                queue.offer(i);
                ans = ans + intToChar(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : node[now].neighbour) {
                node[i].degree--;
                if (node[i].degree == 0) {
                    queue.offer(i);
                    ans = ans + intToChar(i);
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (node[i].degree != 0) {
                return "";
            }
        }
        return ans;
    }

    public char intToChar(int i) {
        return (char) ('a' + i);
    }

    public int charToInt(char ch) {
        return ch - 'a';
    }

    public class Node {
        public int degree;
        public ArrayList<Integer> neighbour = new ArrayList<Integer>();
        void Node() {
            degree = 0;
        }
    }

}
