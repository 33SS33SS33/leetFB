package easy;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**用哈希表统计或者直接排序就行*/
public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram r = new ValidAnagram();
        String a = "8778";
        String b = "8877";
        System.out.println(r.isAnagram(a, b));
    }

    public boolean isAnagram(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        Arrays.sort(S);
        Arrays.sort(T);
        return Arrays.equals(S, T);
    }

}
