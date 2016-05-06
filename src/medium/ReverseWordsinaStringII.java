package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */

/**输入的字符串不包含前缀或者后缀空格，然后字符串只有以单个空格分隔。
 要求不开辟任何其他存储空间，在原先字符串上进行替换。*/
public class ReverseWordsinaStringII {
    public static void main(String[] args) {
        String s1 = "asannbc 21212";
        char[] c1 = s1.toCharArray();
        new ReverseWordsinaStringII().reverseWords(c1);
        System.out.println(c1);
    }

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length);
        int nextWordStart = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, nextWordStart, i);
                nextWordStart = i + 1;
            }
        }
        reverse(s, nextWordStart, s.length);
    }
    void reverse(char[] s, int st, int ed) {
        int l = ed - st;
        for (int i = st; i < st + l / 2; i++) {
            swap(s, i, ed + st - i - 1);
        }
    }
    void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}
