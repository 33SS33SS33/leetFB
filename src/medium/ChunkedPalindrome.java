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
        ChunkedPalindrome sol = new ChunkedPalindrome();
        int res = sol.countChunk("aaaaaa");
        System.out.println(res);
    }

    public int countChunk(String str) {
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

   /* public static int chunkNum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int[][] DP = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    DP[i][j] = 1;
                } else {
                    int sum = 0;
                    int mid = i + (j - i) / 2;
                    for (int count = i; count <= mid; count++) {
                        String pre = s.substring(i, count + 1);
                        String post = s.substring(j - count + i, j + 1);
                        if (pre.equals(post)) {
                            sum += DP[count + 1][j - count + i - 1];
                        }
                    }
                    DP[i][j] = sum;
                }
            }
        }
        return DP[0][length - 1];
    }*/
}
