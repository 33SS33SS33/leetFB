package aTOP50Microsoft;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWordsinaString {
    public String reverseWordsinaString(String s) {
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

    public String reverseWords(String s) {
        // remove leading spaces
        s = s.trim();
        // split by multiple spaces
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
