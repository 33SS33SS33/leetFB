package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 * 8Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * For example,
 * given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total
 * and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each,
 * his h-index is 3.
 * 这道题的切入点应该是这样  h这个值是和几篇文章有关  而只有数组的索引是和文章的数量有关 所以关注点应该在索引上
 * 首先把数组按倒序排列 Then, we look for the last position in which f is greater than or equal to the position (we call h this position).
 * For example, if we have a researcher with 5 publications A, B, C, D, and E with 10, 8, 5, 4, and 3 citations, respectively,
 * the h index is equal to 4 because the 4th publication has 4 citations and the 5th has only 3. On the contrary if the same publications have 25, 8, 5, 3, and 3,
 * then the index is 3 because the fourth paper has only 3 citations.
 * f(A)=10, f(B)=8, f(C)=5, f(D)=4, f(E)=3　→ h-index=4
 * f(A)=25, f(B)=8, f(C)=5, f(D)=3, f(E)=3　→ h-index=3
 * O(N)的算法类似 先过一遍数组 数组的索引是文章的引用次数 如果引用次数大于n了 就放在n索引里 然后值就是这个引用次数出现了几次  所以第n索引的是说出现了多少个大于n的引用
 * 然后也是倒序遍历 找到第一个累加起来超过当前索引的值
 */
public class HIndex {
    public static void main(String[] args) {
        int[] nums = { 3, 0, 6, 1, 5 };
        System.out.println(new HIndex().hIndex(nums));
    }

    /**
     * O(n) time 最好的
     */
    public int hIndex(int[] citations) {
        int length = citations.length;
        if (length == 0) {
            return 0;
        }
        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (citations[i] > length) {
                array2[length] += 1;
            } else {
                array2[citations[i]] += 1;
            }
        }
        int t = 0;
        int result = 0;
        for (int i = length; i >= 0; i--) {
            t = t + array2[i];
            if (t >= i) {
                return i;
            }
        }
        return 0;
    }

}
