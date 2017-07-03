package aFB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 * Tags: String
 */
class TextJustification {
    public static void main(String[] args) {
        System.out.println(fullJustifya(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16)
                .toString());
        System.out.println(fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16)
                .toString());
    }

    //http://www.programcreek.com/2014/05/leetcode-text-justification-java/
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return result;
        }
        int count = 0;
        int last = 0;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            count = count + words[i].length();
            if (count + i - last > maxWidth) {
                int wordsLen = count - words[i].length();
                int spaceLen = maxWidth - wordsLen;
                int eachLen = 1;
                int extraLen = 0;
                if (i - last - 1 > 0) {
                    eachLen = spaceLen / (i - last - 1);
                    extraLen = spaceLen % (i - last - 1);
                }
                StringBuilder sb = new StringBuilder();
                for (int k = last; k < i - 1; k++) {
                    sb.append(words[k]);
                    int ce = 0;
                    while (ce < eachLen) {
                        sb.append(" ");
                        ce++;
                    }
                    if (extraLen > 0) {
                        sb.append(" ");
                        extraLen--;
                    }
                }
                sb.append(words[i - 1]);//last words in the line
                //if only one word in this line, need to fill left with space
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                result.add(sb.toString());
                last = i;
                count = words[i].length();
            }
        }
        int lastLen = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = last; i < words.length - 1; i++) {
            count = count + words[i].length();
            sb.append(words[i] + " ");
        }
        sb.append(words[words.length - 1]);
        int d = 0;
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        result.add(sb.toString());
        return result;
    }

    public static List<String> fullJustifya(String[] words, int L) {
        List<String> list = new LinkedList<String>();
        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }
            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) { // not 1 char, not last line
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }
        return list;
    }

}