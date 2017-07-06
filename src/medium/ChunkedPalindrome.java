package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/4/14.
 * 给定一个字符串，找出最多有多少个chunked palindrome,
 * 正常的palindrome是abccba, chunked palindrome的定义是：比如volvo, 可以把vo划分在一起，(vo) (l) (vo)，
 * 那么它是个palindrome。求实现返回最大的chunk 数量。
 * 比如aaaaaa可以是(aaa)(aaa), 但是最大chunk数量应该是(a)(a)(a)(a)(a)(a)为6
 */
public class ChunkedPalindrome {
    public static void main(String[] args) {
        System.out.println(countChunk("aaaaaa"));
    }

    public static int countChunk(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int sum = 0;
        int l = 0, r = str.length() - 1;
        int preL = l, preR = r;
        while (l < r) {
            String left = str.substring(preL, l + 1);
            String right = str.substring(r, preR + 1);
            if (left.equals(right)) {
                preL = l + 1;
                preR = r - 1;
                sum += 2;
            }
            l++;
            r--;
        }
        if (preL <= preR)
            sum += 1;
        return sum;
    }

}
