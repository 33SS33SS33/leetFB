package aMaz;

/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Note:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 * Tags: String
 * split用来分割 返回的是分割之后的list 默认是用空格
 * join用来连接 join最前的字符表示用什么字符连接 
 * [::-1]用来颠倒
 * 下次写一个不用这些函数的
 * 进步步骤就是 先去空格 头空格 中间多的空格 尾部空格
 * 然后颠倒整个list
 */

class ReverseWordsinaString {
    public static void main(String[] args) {
        String given = "the sky is blue";
        String given2 = "    a    b";
        String given3 = "the sky is blue";
        System.out.println(new ReverseWordsinaString().reverseWords(given));
        System.out.println(new ReverseWordsinaString().reverseWordsB(given2));
        System.out.println(new ReverseWordsinaString().reverseWordsB(given3));
    }

    /**
     * If space, continue
     * If not, get the word and insert to the front of result
     * note that result may not contain spaces before or after
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            else {
                StringBuilder word = new StringBuilder();
                while (i < s.length()) {
                    c = s.charAt(i);
                    if (c == ' ')
                        break;
                    word.append(c);
                    i++;
                }
                res = res.length() == 0 ?
                        word.toString() :
                        word.toString() + " " + res; // insert to front of res
                i--; // reset i
            }
        }
        return res;
    }

    /**
     * Trim input string
     * Split it with a space
     * Traversal backwards
     * Trim result to remove last space
     */
    public String reverseWordsB(String s) {
        if (s == null || s.length() == 0)
            return "";
        s = s.trim();
        StringBuilder res = new StringBuilder();
        String[] words = s.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                res.append(words[i]);
                if (i != 0)
                    res.append(" ");
            }
        }
        return res.toString(); // remove last space
    }

}
