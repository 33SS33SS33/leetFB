package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Given an input string , reverse the string word by word.
 * Example:
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Note:
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * Follow up: Could you do it in-place without allocating extra space?
 * <p>
 * 输入的字符串不包含前缀或者后缀空格，然后字符串只有以单个空格分隔。
 * 要求不开辟任何其他存储空间，在原先字符串上进行替换。
 * 字符串是按照单个字符存进数组的
 * 所以就先把数组全反转过来 然后在从头到尾遍历 每次碰到了空格 就把这个单词给反转回来
 */
public class ReverseWordsinaStringII {
    public static void main(String[] args) {
        String s1 = "the sky is blue";
        char[] c1 = s1.toCharArray();
        new ReverseWordsinaStringII().reverseWords(c1);
        System.out.println(c1);
    }

    public void reverseWords(char[] s) {
        // Three step to reverse
        // 1, reverse the whole sentence
        reverse(s, 0, s.length - 1);
        // 2, reverse each word
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
        // 3, reverse the last word, if there is only one word this will solve the corner case
        reverse(s, start, s.length - 1);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
