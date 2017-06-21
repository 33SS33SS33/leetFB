package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * edit distance就是指当前的字符串是否能通过替换一个或者添加或者删除一个字符来变成另外一个字符串
 * 主要就是先通过长度来判断  长度相等的话就 挨个比对
 * 如果长度差1的话 就找到第一个不同的字符  然后把长的串的这个字符插入进短的 看相同不相同
 */

//Given two strings S and T, determine if they are both one edit distance apart.
public class OneEditDistance {
    public static void main(String[] args) {
        System.out.print(new OneEditDistance().isOneEditDistanceaa("wwq3", "ww"));
    }

    /**
     * 最好的
     */
    public boolean isOneEditDistanceaa(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length())
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length())
                    return s.substring(i).equals(t.substring(i + 1));
                else
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //重要  ！All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

}
