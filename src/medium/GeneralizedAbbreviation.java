package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = ""word"", return the following list (order does not matter):
 * [""word"", ""1ord"", ""w1rd"", ""wo1d"", ""wor1"", ""2rd"", ""w2d"", ""wo2"", ""1o1d"",
 * ""1or1"", ""w1r1"", ""1o2"", ""2r1"", ""3d"", ""w3"", ""4""]"
 * "这道题没有太好的思路 照着别人的打了一遍
 * 大体思路是这样的 可以把单词分割 一部分是直接开始变成缩写 剩下的部分就递归调用函数求出剩下的组合"

 * The idea is: for every character, we can keep it or abbreviate it.
 * To keep it, we add it to the current solution and carry on backtracking.
 * To abbreviate it, we omit it in the current solution, but increment the count,
 * which indicates how many characters have we abbreviated. When we reach the end or need to put
 * a character in the current solution, and count is bigger than zero, we add the number into the solution.*/
public class GeneralizedAbbreviation {
    public static void main(String[] args) {
        String S = "word";
        System.out.println(generateAbbreviations(S));
    }
    public static List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<String>();
        backtrack(ret, word, 0, "", 0);

        return ret;
    }

    private static void backtrack(List<String> ret, String word, int pos, String cur, int count) {
        if (pos == word.length()) {
            if (count > 0) cur += count;
            ret.add(cur);
        } else {
            backtrack(ret, word, pos + 1, cur, count + 1);
            backtrack(ret, word, pos + 1, cur + (count > 0 ? count : "") + word.charAt(pos), 0);
        }
    }
}
