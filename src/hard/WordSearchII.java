package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
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
 * 使用了字典树 trie tree 然后和第一题的答案结合起来即可
 * 因为是一个字母一个字母的搜索 所以哈希表不行 必须用字典树才能比较有效率
 */
public class WordSearchII {
    public static void main(String[] args) {
        WordSearchII s = new WordSearchII();
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};

        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(new WordSearchII().wordSearchII(board, words));
        //        System.out.println(exist2(board, word));
    }

    /**
     * 最好的 Backtracking + Trie     没看懂
     */
    public List<String> wordSearchII(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null)
            return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    // 怎么建立字典树
    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

}
