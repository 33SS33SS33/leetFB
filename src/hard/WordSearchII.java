package hard;

import com.sun.org.apache.xml.internal.utils.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
 * those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 */

/**
 * 使用了字典树 trie tree 然后和第一题的答案结合起来即可
 * 因为是一个字母一个字母的搜索 所以哈希表不行 必须用字典树才能比较有效率
 */
public class WordSearchII {
    public static void main(String[] args) {
        WordSearchII s = new WordSearchII();
        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };

        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(new WordSearchII().findWordsB(board, words));
        //        System.out.println(exist2(board, word));
    }

    Set<String> res = new HashSet<String>();

/*    public List<String> findWordsA(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;
        if (visited[x][y])
            return;

        str += board[x][y];
        if (!trie.startsWith(str))
            return;

        if (trie.search(str)) {
            res.add(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }*/

    public  List<String> findWordsB(char[][] board, String[] words) {
        TrieNode root = new TrieNode(null, '\0');
        for (String w : words) {
            root.insert(w.toCharArray(), 0, w.length());
        }
        final int LEN = board.length * board[0].length;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (root.hasChild(board[x][y])) {
                    findWords(x, y, board, new boolean[LEN], root.child(board[x][y]));
                }
            }
        }
        return new ArrayList<String>(found);
    }

    void findWords(int x, int y, char[][] board, boolean[] vi, TrieNode current) {
        vi[flatten(x, y, board[0].length)] = true;
        if (current.count > 0) {
            found.add(current.recover());
        }
        for (int[] xy : new int[][] { { x + 1, y }, { x, y + 1 }, { x - 1, y }, { x, y - 1 }, }) {
            int _x = xy[0];
            int _y = xy[1];

            if (!vaild(_x, _y, board)) {
                continue;
            }
            if (vi[flatten(_x, _y, board[0].length)]) {
                continue;
            }
            TrieNode t = current.child(board[_x][_y]);
            if (t == null) {
                continue;
            }
            findWords(_x, _y, board, vi, t);
            vi[flatten(_x, _y, board[0].length)] = false;
        }
    }

    int flatten(int x, int y, int wide) {
        return x * wide + y;
    }

    boolean vaild(int x, int y, char[][] board) {
        return x >= 0 &&
                y >= 0 &&
                x < board.length &&
                y < board[0].length;
    }

    Set<String> found = new HashSet<String>();

    static class TrieNode {
        // Initialize your data structure here.
        TrieNode parent;
        int depth = 0;
        char character;
        TrieNode[] children = new TrieNode[26];
        int        count    = 0;

        public TrieNode(TrieNode parent, char character) {
            this.parent = parent;
            this.character = character;

            if (parent != null) {
                this.depth = parent.depth + 1;
            }
        }

        TrieNode safe(char c) {
            int i = index(c);
            if (children[i] == null) {
                children[i] = new TrieNode(this, c);
            }
            return children[i];
        }

        int index(char c) {
            return (int) (c - 'a');
        }

        void insert(char[] word, int st, int len) {
            if (len == 0) {
                this.count++;
                return;
            }
            TrieNode t = safe(word[st]);
            t.insert(word, st + 1, len - 1);
        }

        TrieNode child(char c) {
            return children[index(c)];
        }

        boolean hasChild(char c) {
            return child(c) != null;
        }

        String recover() {
            // assert count > 0
            TrieNode t = this;
            char[] s = new char[depth];
            for (int i = depth - 1; i >= 0; i--) {
                s[i] = t.character;
                t = t.parent;
            }
            return new String(s);
        }
    }
}
