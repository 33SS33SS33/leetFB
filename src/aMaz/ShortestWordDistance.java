package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 两个变量 一个存word1得位置  一个存word2得位置 每次碰见就计算一次距离
 */
public class ShortestWordDistance {
    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";
        System.out.println(shortestWordDistance(words, word1, word2));
    }

    public static int shortestWordDistance(String[] words, String word1, String word2) {
        int len = words.length;
        int i1 = -1;
        int i2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                i1 = i;
            } else if (word2.equals(words[i])) {
                i2 = i;
            }
            if (i1 >= 0 && i2 >= 0) {
                len = Math.min(len, Math.abs(i1 - i2));
            }
        }
        return len;
    }

}
