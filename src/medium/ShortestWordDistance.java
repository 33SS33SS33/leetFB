package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class ShortestWordDistance {
    public static void main(String[] args) {
        String[] words = { "dog", "cat" };
        String word1 = "cog";
        String word2 = "car";
        int res = new ShortestWordDistance().shortestDistance(words, word1, word2);
        System.out.println(res);
    }

    public int shortestDistance(String[] words, String word1, String word2) {
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
