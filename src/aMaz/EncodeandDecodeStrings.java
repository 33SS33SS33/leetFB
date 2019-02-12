package aMaz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Design an algorithm to encodeandDecodeTinyURL a list of strings to a string. The encoded string is then sent over the network
 * and is decoded back to the original
 * list of strings.
 * Machine 1 (sender) has the function:
 * string encodeandDecodeTinyURL(vector<string> strs) {
 * // ... your code
 * return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decodeTinyURL(string s) {
 * //... your code
 * return strs;
 * }
 * So Machine 1 does:
 * string encoded_string = encodeandDecodeTinyURL(strs);
 * and Machine 2 does:
 * vector<string> strs2 = decodeTinyURL(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encodeandDecodeTinyURL and decodeTinyURL methods."
 * 把字符串合并成  字符串长度+冒号+字符串这种形式
 * 然后在还原的时候 只需要先find冒号 然后通过字符串长度把字符串切分出来即可 因为是用的前缀的表达 所以不用担心字符串里出现冒号的情况"
 */
public class EncodeandDecodeStrings {
    public static void main(String[] args) {
        EncodeandDecodeStrings d = new EncodeandDecodeStrings();
        List<String> sList = new ArrayList<>();
        sList.add("iiy");
        sList.add("krystal");
        sList.add("happy");
        String res = d.encodeStrings(sList);
        List<String> resLis = d.decodeStrings(res);
        System.out.println(res);
        System.out.println(resLis);
    }

    // Encodes a list of strings to a single string.
    public String encodeStrings(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decodeStrings(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, slash));
            ret.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return ret;
    }

   /* static final int MAX_LEN = Integer.toHexString(Integer.MAX_VALUE).length();
    // lazy ... should be byte[]
    static final String NUM_PATTERN = "%0" + MAX_LEN + "x";
    *//*  [count] [str len] [str  ...]             ... [str len][str  ...  ]
     *  0       L         2L        2L + strlen      nL       (n + 1)L   (n + 1)L + strlen
     *//*

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        sb.append(serializeNumber(strs.size()));
        for (String s : strs) {
            sb.append(serializeNumber(s.length()));
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        char[] S = s.toCharArray();
        int offset = 0;
        int n = deserializeNumber(S, offset);
        // move to first str len start
        offset += MAX_LEN;
        ArrayList<String> strs = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            int len = deserializeNumber(S, offset);
            // move to str start
            offset += MAX_LEN;
            strs.add(new String(S, offset, len));
            // move to next str len start
            offset += len;
        }
        return strs;
    }

    String serializeNumber(int n) {
        return String.format(NUM_PATTERN, n);
    }

    int deserializeNumber(char[] s, int offset) {
        return Integer.parseInt(new String(s, offset, MAX_LEN), 16);
    }*/

}
