package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**edit distance就是指当前的字符串是否能通过替换一个或者添加或者删除一个字符来变成另外一个字符串
 主要就是先通过长度来判断  长度相等的话就 挨个比对
 如果长度差1的话 就找到第一个不同的字符  然后把长的串的这个字符插入进短的 看相同不相同*/
public class OneEditDistance {
    public static void main(String[] args) {
        System.out.print(new OneEditDistance().isOneEditDistance("wwq3","ww"));
    }

    public boolean isOneEditDistance(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        // make sure s is always to shorter one
        if (S.length > T.length)
            return isOneEditDistance(t, s);
        if (T.length - S.length > 1)
            return false;
        int diff = 0;
        int i = 0;
        int j = 0;
        for (; i < S.length; i++, j++) {
            if (S[i] != T[j]) {
                diff++;
                if (diff > 1)
                    return false;
                // len(s) + 1 = len(t)
                if (S.length != T.length && S[i] == T[j + 1]) {
                    j++; // delete one from T
                }
            }
        }
        if (diff == 0) {
            return j + 1 == T.length;
        }
        return j == T.length;
    }
}
