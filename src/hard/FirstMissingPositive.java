package hard;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 * Tags: Array
 */

/**
 * 使用了类似桶排序的办法 扫描两边
 * 第一遍的目的是要让数组的每个元素值i 在索引i-1的位置上 比如a[0]上就要存储1 a[1]就要存储2  这样第二次扫描的时候碰到第一个a[i] != i+1的就是缺少的位置
 * 这里安排位置就使用的是交换  如果值是小于0 或者大于数组长度的 这些元素在哪无所谓 就跳过  还有就是 如果交换的两个元素的值是相等的 也要跳过  要不会无限循环
 * 桶排序的思路是这样的 如果有十个数字0-9 那么我就给十个桶的话 只需要把每个数字放入对应编号的桶里就好了  比如9就进9号桶 然后从0号桶开始就是排序好的数字  桶排序不需要用到比大小
 */
class FirstMissingPositive {
    public static void main(String[] args) {
        int[] A = { 3, 4, -1, 1 };
        System.out.println(new FirstMissingPositive().firstMissingPositive(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveB(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveD(A));
    }

    /**
     * Position of integer n should be n - 1 if sorted
     * Correct form [1, 2, 3, 4, ..., #, n]
     * If not in position swap it with A[A[p]-1]
     */
    public static int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0)
            return 1;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int num = A[i];
            while (A[i] <= n && A[i] > 0 && A[num - 1] != num) {
                A[i] = A[num - 1];
                A[num - 1] = num;
                num = A[i];
            }
        }
        for (int i = 0; i < n; i++)
            if (A[i] != i + 1)
                return i + 1;
        return n + 1; // nothing in middle losing, return largest
    }

    public int firstMissingPositiveB(int[] A) {
        final int N = A.length;
        next:
        for (int i = 0; i < N; i++) {
            int v = A[i];
            if (v == i + 1)
                continue;
            while (true) {
                if (v <= 0 || v > N) {
                    continue next;
                }
                int t = A[v - 1];
                if (t == v) {
                    continue next;
                }
                A[v - 1] = v;
                v = t;
            }
        }
        for (int i = 0; i < N; i++) {
            int v = A[i];
            if (v != i + 1) {
                return i + 1;
            }
        }
        return N + 1;
    }

    /**creek This problem only considers positive numbers, so we need to shift 1 offset. The ith element is i+1.*/
    public int firstMissingPositiveD(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1) {
                if (A[i] <= 0 || A[i] >= n)
                    break;
                if (A[i] == A[A[i] - 1])
                    break;
                int temp = A[i];
                A[i] = A[temp - 1];
                A[temp - 1] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

}