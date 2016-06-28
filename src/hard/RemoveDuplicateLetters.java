package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "Given a string which contains only lowercase letters, remove duplicate letters
 * so that every letter appear once and only once. You must make sure your result is the smallest
 * in lexicographical order among all possible results.
 * Example:
 * Given ""bcabc""
 * Return ""abc""
 * Given ""cbacdcbc""
 * Return ""acdb""
 * "贪心的算法核心就是以每个唯一的字符为一个阶段 然后尽量找这个阶段的最小字典序
 * 从开始遍历字符串，直到遇到首个唯一字符为止，从前缀中挑选出字典序最小的作为首字符。
 * 然后从剩余字符串中移除所有与首字母相同的字母。
 * 重复此过程，直到选出所有唯一字符为止。
 * Python的代码 其实用的是这个思路  他的逻辑是 按照排序过的set来选字符   然后把从他开始的后缀截取出来
 * 如果这个后缀的set和s的set一样  就说明当前这个字符是首个唯一字符或者首个唯一字符这些之前的字符  然后把他去掉
 * 因为已经是排序的 所以这个字符肯定也是字典序最小的  就可以作为首字符了"
 * "	https://leetcode.com/discuss/73761/a-short-o-n-recursive-greedy-solution
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String s = "bcabc";
        String s2 = "cbacdcbc";
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters(s));
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters(s2));
    }

    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++)
            cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos))
                pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0)
                break;
        }
        return s.length() == 0 ?
                "" :
                s.charAt(pos) + removeDuplicateLetters(
                        s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}
