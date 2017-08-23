package medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * You may assume that each word will contain only lower case letters. If no such two words wordSearchb, return 0.
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words./
 * "首先把每一个word都转化成位的表示  就是一共26位 这个word里的字母对应的位置就变成1
 * 然后把这些word分别做& 如果结果为0就计算一下他们的乘积
 * reduce里第三个参数可以设置一个初始值  这个值其实就是插入到了你这个数组的最前面  方便计算 重要"
 */
public class MaximumProductofWordLengths {
    public static void main(String[] args) {
        MaximumProductofWordLengths l = new MaximumProductofWordLengths();
        String[] s = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(l.maxProduct(s));
        System.out.println(l.maxProductB(s));
    }

    public int maxProduct(String[] words) {
        int max = 0;
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });
        int[] masks = new int[words.length]; // alphabet masks
        for (int i = 0; i < masks.length; i++) {
            for (char c : words[i].toCharArray()) {
                masks[i] |= 1 << (c - 'a');
            }
        }
        for (int i = 0; i < masks.length; i++) {
            if (words[i].length() * words[i].length() <= max)
                break; //prunning
            for (int j = i + 1; j < masks.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }
        return max;
    }

    public static int maxProductB(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                value[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++) {
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length()
                        > maxProduct))
                    maxProduct = words[i].length() * words[j].length();
            }
        return maxProduct;
    }

}
