package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * 用二分 和上题类似 但是没搞懂原理 还需要研究
 * 要从hindex的定义入手 h是合法的时候 此时数组有h个文章的引用不超过3 有n-h个文章的引用不小于3
 * 在二分查找的时候 右边表示是有n-mid个文章引用不小于当前mid的引用 所以查找的目标就是找到的这个位置L 所以只要找到这个位置有n-mid个文章
 * 等于当前这个位置的引用 那就是符合要求的 并且n-mid就是h (因为排过序了 只要n-mid这边是对的 那么左边也一定是对的)
 * 比如这个[1,3,5,8,25] 首先中间的值找到了 是2 然后引用次数是5 但是n-mid 只有2 说明只有两个文章引用超过了5 我们的目标是找到n-mid等于引用次数的
 * 所以必须减少引用次数 并且增加n-mid 所以只能往左找
 */
public class HIndex2 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 5, 6};
        System.out.println(new HIndex2().hIndex(nums));
    }

    //https://discuss.leetcode.com/topic/23399/standard-binary-search/2
    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        int l = 0, r = citations.length;
        int n = citations.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (citations[mid] == n - mid)
                return n - mid;
            if (citations[mid] < n - mid)
                l = mid + 1;
            else r = mid;
        }
        return n - l;
    }

}
