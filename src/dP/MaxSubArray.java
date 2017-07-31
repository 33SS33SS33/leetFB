package dP;

/**
 * Created by GAOSHANSHAN835 on 2015/12/31.
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] s1 = {1, 2, 5, 1, -1};
        System.out.println(new MaxSubArray().maxSubArray2(s1));
    }

    /**
     * 3.8遇到 经典动归 最好的
     * 时间上只需要扫描一次数组，所以时间复杂度是O(n)。空间上我们可以看出表达式中只需要用到上一步local[i]和global[i]就可以得到下一步的结果，
     * 所以我们在实现中可以用一个变量来迭代这个结果，不需要是一个数组，也就是如程序中实现的那样，所以空间复杂度是两个变量（local和global），即O(2)=O(1)
     */
    public int maxSubArray2(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int maxEndingHere = A[0], maxSoFar = A[0];
        for (int i = 1; i < A.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

}
