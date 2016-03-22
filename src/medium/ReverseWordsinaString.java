package medium;

/**
 * Given an input string, reverse the string word by word.
 * <p/>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p/>
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * <p/>
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing
 * spaces.
 * <p/>
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 * <p/>
 * Tags: String
 */
class ReverseWordsinaString {
    public static void main(String[] args) {
        String given = "the sky is blue";
        String given2 = "    a    b";
        String given3 = "the sky is blue";
        String given4 = "the sky is blue";
        System.out.println(new ReverseWordsinaString().reverseWords(given));
        System.out.println(new ReverseWordsinaString().reverseWordsB(given2));
        System.out.println(new ReverseWordsinaString().reverseWordsB(given3));
        System.out.println(new ReverseWordsinaString().reverseWordsC(given4));
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

    /**
     * ------creek----
     */
    public String reverseWordsC(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // split to words by space
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
